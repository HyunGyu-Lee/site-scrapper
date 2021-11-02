package com.hst.scrapper.modules.scrap.domain.type

import java.util.*

/**
 * @author dlgusrb0808@gmail.com
 */
enum class MetadataType(
    val tagName: String
) {
    OG_TITLE("og:title"),
    OG_IMAGE("og:image"),
    OG_DESCRIPTION("og:description"),
    OG_URL("og:url"),
    TWITTER_IMAGE("twitter:image");

    companion object {
        fun find(tagName: String): MetadataType =
            EnumSet.allOf(MetadataType::class.java).first { it.tagName == tagName }
    }
}