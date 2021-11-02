package com.hst.scrapper.modules.category.infrastructure.jpa

import com.hst.scrapper.modules.category.domain.entity.Category
import com.hst.scrapper.modules.category.domain.repo.CategoryRepository
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author dlgusrb0808@gmail.com
 */
interface JpaCategoryRepository: CategoryRepository, JpaRepository<Category, Long>