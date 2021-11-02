package com.hst.scrapper.modules.scrap.domain.repo

import com.hst.scrapper.modules.scrap.domain.entity.Scrap
import com.hst.scrapper.modules.user.domain.entity.User

/**
 * @author dlgusrb0808@gmail.com
 */
interface ScrapRepository {
    fun findById(id: Long): Scrap?

    fun save(scrap: Scrap): Scrap

    fun findByScrapedUserOrderByIdDesc(user: User): List<Scrap>

    fun delete(scrap: Scrap)
}