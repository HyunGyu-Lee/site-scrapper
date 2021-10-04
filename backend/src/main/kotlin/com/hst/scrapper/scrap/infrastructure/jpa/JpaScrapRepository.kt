package com.hst.scrapper.scrap.infrastructure.jpa

import com.hst.scrapper.scrap.domain.entity.Scrap
import com.hst.scrapper.scrap.domain.repo.ScrapRepository
import com.hst.scrapper.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author dlgusrb0808@gmail.com
 */
interface JpaScrapRepository : ScrapRepository, JpaRepository<Scrap, Long>