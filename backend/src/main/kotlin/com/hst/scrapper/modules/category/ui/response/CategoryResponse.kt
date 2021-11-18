package com.hst.scrapper.modules.category.ui.response

import com.hst.scrapper.modules.category.domain.entity.Category

/**
 * @author dlgusrb0808@gmail.com
 */
data class CategoryResponse(
    val id: Long,
    val name: String,
    val order: Int,
    var children: List<CategoryResponse> = emptyList()
) {
    companion object {
        fun from(entity: Category): CategoryResponse {
            val response = CategoryResponse(entity.id, entity.name, entity.order)
            response.children = entity.childCategories.map { from(it) }
            return response
        }
    }
}