package com.hst.scrapper.repo

import com.hst.scrapper.modules.scrap.infrastructure.web.JsoupWebMetadataReader
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

/**
 * @author dlgusrb0808@gmail.com
 */
class JsoupWebMetadataReaderTest {

    val log = LoggerFactory.getLogger(JsoupWebMetadataReaderTest::class.java)

    val reader = JsoupWebMetadataReader()

    @Test
    fun `URL 패턴 매치 테스트`() {
        val webMeta = reader.read("https://cafe.naver.com/slayergear2/104295")
        with(webMeta) {
            log.info("url: $url")
            log.info("title: $title")
            log.info("imageUrl: $imageUrl")
            log.info("description: $description")
        }
    }

}