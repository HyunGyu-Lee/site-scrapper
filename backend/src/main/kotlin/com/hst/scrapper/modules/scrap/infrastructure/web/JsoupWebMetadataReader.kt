package com.hst.scrapper.modules.scrap.infrastructure.web

import com.hst.scrapper.modules.scrap.domain.exception.InvalidScrapUrlException
import com.hst.scrapper.modules.scrap.domain.repo.WebMetadataReader
import com.hst.scrapper.modules.scrap.domain.type.MetadataType
import com.hst.scrapper.modules.scrap.domain.vo.WebMetadata
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Repository

/**
 * @author dlgusrb0808@gmail.com
 */
@Repository
class JsoupWebMetadataReader : WebMetadataReader {

    override fun read(url: String): WebMetadata {
        if (url.isEmpty()) {
            throw InvalidScrapUrlException().addAttribute("url", url)
        }
        val document = Jsoup.connect(url).get()
        val metadata = mutableMapOf<MetadataType, String>()
        readOpenGraphTag(document, metadata)
        adjustData(document, metadata)
        return WebMetadata(
            url = metadata.getOrDefault(MetadataType.OG_URL, ""),
            title = metadata.getOrDefault(MetadataType.OG_TITLE, ""),
            imageUrl = metadata.getOrDefault(MetadataType.OG_IMAGE, ""),
            description = metadata.getOrDefault(MetadataType.OG_DESCRIPTION, ""),
        )
    }

    private fun readOpenGraphTag(document: Document, metadata: MutableMap<MetadataType, String>) {
        val metaTags = document.getElementsByTag("meta")
        for (metaTag in metaTags) {
            val property = metaTag.attr("property")
            if (MetadataType.values().any { it.tagName == property }) {
                metadata[MetadataType.find(property)] = metaTag.attr("content")
            }
        }
    }

    // 빈 데이터를 대체해주고, 잘못된 데이터는 조정해주는 메소드
    private fun adjustData(document: Document, metadata: MutableMap<MetadataType, String>) {
        // Site Url
        metadata.putIfAbsent(MetadataType.OG_URL, document.baseUri())
        if (metadata[MetadataType.OG_URL]!!.endsWith("/")) {
            metadata[MetadataType.OG_URL] =
                metadata[MetadataType.OG_URL]!!.substring(0, metadata[MetadataType.OG_URL]!!.length - 1)
        }

        metadata.putIfAbsent(MetadataType.OG_TITLE, document.head().tagName("title").`val`())
        metadata.putIfAbsent(MetadataType.OG_DESCRIPTION, metadata[MetadataType.OG_TITLE]!!)
        var imageUrl = metadata[MetadataType.OG_IMAGE]
        if (imageUrl != null && !imageUrl.startsWith("http")) {
            imageUrl = if (imageUrl.startsWith("/")) {
                imageUrl.substring(1)
            } else {
                imageUrl
            }
            metadata[MetadataType.OG_IMAGE] = "${metadata[MetadataType.OG_URL]}/$imageUrl"
        }
    }

}