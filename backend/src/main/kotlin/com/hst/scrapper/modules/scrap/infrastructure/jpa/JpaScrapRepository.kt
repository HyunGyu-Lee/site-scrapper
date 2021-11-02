package com.hst.scrapper.modules.scrap.infrastructure.jpa

import com.hst.scrapper.modules.scrap.domain.entity.Scrap
import com.hst.scrapper.modules.scrap.domain.repo.ScrapRepository
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author dlgusrb0808@gmail.com
 */
interface JpaScrapRepository : ScrapRepository, JpaRepository<Scrap, Long>