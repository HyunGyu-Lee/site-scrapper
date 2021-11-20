package com.hst.scrapper.modules.scrap.ui.request

/**
 * @author dlgusrb0808@gmail.com
 */
data class CreateScrapRequest(
    val url: String,
    val userId: Long,
    val categoryId: Long
)