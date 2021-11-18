package com.hst.scrapper.modules.category.ui.request

/**
 * @author dlgusrb0808@gmail.com
 */
data class CategoryCreateRequest(val parentId: Long = 0, val name: String, val userId: Long)
