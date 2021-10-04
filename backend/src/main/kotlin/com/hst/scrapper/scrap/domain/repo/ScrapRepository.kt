package com.hst.scrapper.scrap.domain.repo

import com.hst.scrapper.scrap.domain.entity.Scrap
import com.hst.scrapper.user.domain.entity.User

/**
 * @author dlgusrb0808@gmail.com
 */
interface ScrapRepository {
    fun findById(id: Long): Scrap?

    fun save(scrap: Scrap): Scrap

    fun findByScrapedUserOrderByIdDesc(user: User): List<Scrap>
}