package com.hst.scrapper.modules.scrap.ui.response

import com.hst.scrapper.modules.scrap.domain.entity.Scrap
import java.time.LocalDateTime

/**
 * @author dlgusrb0808@gmail.com
 */
data class ScrapResponse(
    val id: Long,
    val url: String,
    val title: String,
    val description: String,
    val image: String?,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(entity: Scrap): ScrapResponse {
            return ScrapResponse(
                entity.id, entity.url, entity.title, entity.description, entity.image, entity.createdAt
            )
        }
    }
}