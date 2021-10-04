package com.hst.scrapper.scrap.domain.application

import com.hst.scrapper.scrap.domain.repo.ScrapRepository
import com.hst.scrapper.scrap.ui.response.ScrapResponse
import com.hst.scrapper.user.application.UserService
import org.springframework.stereotype.Service

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
class ScrapService(
    val userService: UserService,
    val scrapRepository: ScrapRepository
) {

    /**
     * [userId] 유저의 스크랩 목록 조회
     */
    fun getScraps(userId: Long): List<ScrapResponse> {
        val user = userService.getUser(userId)
        return scrapRepository.findByScrapedUserOrderByIdDesc(user).map { ScrapResponse.from(it) }
    }

}