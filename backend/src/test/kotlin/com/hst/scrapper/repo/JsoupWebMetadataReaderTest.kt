package com.hst.scrapper.repo

import com.hst.scrapper.scrap.infrastructure.web.JsoupWebMetadataReader
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * @author dlgusrb0808@gmail.com
 */
class JsoupWebMetadataReaderTest {

    val log = LoggerFactory.getLogger(JsoupWebMetadataReaderTest::class.java)

    val reader = JsoupWebMetadataReader()

    @Test
    fun `URL 패턴 매치 테스트`() {
        val webMeta = reader.read("https://spring.io/")
        with(webMeta) {
            log.info("url: $url")
            log.info("title: $title")
            log.info("imageUrl: $imageUrl")
            log.info("description: $description")
        }
    }

}