package com.hst.scrapper.modules.category.domain.repo

import com.hst.scrapper.modules.category.domain.entity.Category
import com.hst.scrapper.modules.user.domain.entity.User

/**
 * @author dlgusrb0808@gmail.com
 */
interface CategoryRepository {

    fun findByUser(user: User): List<Category>

    fun save(category: Category): Category

}