package com.hst.scrapper.modules.category.ui

import com.hst.scrapper.global.model.api.ApiResponse
import com.hst.scrapper.modules.category.application.CategoryService
import com.hst.scrapper.modules.category.ui.request.CategoryCreateRequest
import com.hst.scrapper.modules.category.ui.request.CategoryModifyRequest
import com.hst.scrapper.modules.category.ui.response.CategoryResponse
import org.springframework.web.bind.annotation.*

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/categories")
class CategoryController(
    val categoryService: CategoryService
) {

    @GetMapping
    fun getUserCategories(@RequestParam userId: Long): ApiResponse<List<CategoryResponse>> {
        val list = categoryService.getUserCategories(userId)
        return ApiResponse.success(list)
    }

    @PostMapping
    fun createCategory(@RequestBody request: CategoryCreateRequest) {
        categoryService.createCategory(request)
    }

    @PutMapping("{id}")
    fun modifyCategory(@PathVariable id: Long, @RequestBody request: CategoryModifyRequest): ApiResponse<Any?> {
        request.id = id
        categoryService.modifyCategory(request)
        return ApiResponse.success()
    }

    @DeleteMapping("{id}")
    fun deleteCategory(@PathVariable id: Long): ApiResponse<Any?> {
        categoryService.deleteCategory(id)
        return ApiResponse.success()
    }

}