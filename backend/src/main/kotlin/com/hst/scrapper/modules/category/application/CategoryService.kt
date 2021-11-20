package com.hst.scrapper.modules.category.application

import com.hst.scrapper.modules.category.domain.entity.Category
import com.hst.scrapper.modules.category.domain.exception.CategoryNotFoundException
import com.hst.scrapper.modules.category.domain.repo.CategoryRepository
import com.hst.scrapper.modules.category.ui.request.CategoryCreateRequest
import com.hst.scrapper.modules.category.ui.request.CategoryModifyRequest
import com.hst.scrapper.modules.category.ui.response.CategoryResponse
import com.hst.scrapper.modules.user.application.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val userService: UserService
) {

    /**
     * [id] 카테고리 조회
     */
    fun getCategory(id: Long) = this.getCategoryEntity(id)

    /**
     * [userId] 유저의 카테고리 목록 조회
     */
    fun getUserCategories(userId: Long): List<CategoryResponse> {
        val user = userService.getUser(userId)
        val categories = categoryRepository.findByUserAndParentCategoryIsNullOrderByOrder(user)
        return categories.map { CategoryResponse.from(it) }
    }

    /**
     * 마테고리 추가
     */
    @Transactional
    fun createCategory(request: CategoryCreateRequest) {
        val category = createNewCategory(request.userId, request.name)
        val parentCategory = categoryRepository.findById(request.parentId)
        if (parentCategory != null) {
            parentCategory.addChild(category)
            categoryRepository.save(parentCategory)
        } else {
            category.changeOrder((categoryRepository.countByUserIdAndParentCategoryIsNull(request.userId) + 1).toInt())
        }
        categoryRepository.save(category)
    }

    private fun createNewCategory(userId: Long, name: String): Category {
        val category = Category()
        category.changeName(name)
        category.user = userService.getUser(userId)
        return category
    }

    /**
     * 카테고리 수정
     */
    @Transactional
    fun modifyCategory(request: CategoryModifyRequest) {
        val category = getCategoryEntity(request.id)
        category.changeName(request.name)
        categoryRepository.save(category)
    }

    /**
     * [id]에 해당하는 카테고리 삭제
     */
    @Transactional
    fun deleteCategory(id: Long) = categoryRepository.delete(getCategoryEntity(id))

    private fun getCategoryEntity(id: Long) =
        categoryRepository.findById(id) ?: throw CategoryNotFoundException().addAttribute("id", id)

}