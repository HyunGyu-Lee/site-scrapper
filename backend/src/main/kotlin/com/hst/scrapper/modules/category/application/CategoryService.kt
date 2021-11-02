package com.hst.scrapper.modules.category.application

import com.hst.scrapper.modules.category.domain.repo.CategoryRepository
import com.hst.scrapper.modules.user.application.UserService
import org.springframework.stereotype.Service

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
class CategoryService(
    val categoryRepository: CategoryRepository,
    val userService: UserService
) {

    fun getUserCategories(userId: Long) {
        val user = userService.getUser(userId)
        val categories = categoryRepository.findByUser(user)
        println(categories)
    }

}