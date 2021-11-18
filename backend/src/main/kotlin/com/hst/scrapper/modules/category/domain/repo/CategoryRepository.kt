package com.hst.scrapper.modules.category.domain.repo

import com.hst.scrapper.modules.category.domain.entity.Category
import com.hst.scrapper.modules.user.domain.entity.User

/**
 * @author dlgusrb0808@gmail.com
 */
interface CategoryRepository {

    fun findById(id: Long): Category?

    fun findByUserAndParentCategoryIsNullOrderByOrder(user: User): List<Category>

    fun countByUserIdAndParentCategoryIsNull(userId: Long): Long

    fun save(category: Category): Category

    fun delete(category: Category)

}