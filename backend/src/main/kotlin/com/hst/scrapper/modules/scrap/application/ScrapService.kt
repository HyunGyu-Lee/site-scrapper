package com.hst.scrapper.modules.scrap.application

import com.hst.scrapper.modules.scrap.domain.entity.Scrap
import com.hst.scrapper.modules.scrap.domain.exception.ScrapNotFoundException
import com.hst.scrapper.modules.scrap.domain.repo.ScrapRepository
import com.hst.scrapper.modules.scrap.domain.repo.WebMetadataReader
import com.hst.scrapper.modules.scrap.ui.request.CreateScrapRequest
import com.hst.scrapper.modules.scrap.ui.response.ScrapResponse
import com.hst.scrapper.modules.user.application.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
class ScrapService(
    private val userService: UserService,
    private val scrapRepository: ScrapRepository,
    private val metadataReader: WebMetadataReader
) {

    /**
     * [userId] 유저의 스크랩 목록 조회
     */
    fun getScraps(userId: Long): List<ScrapResponse> {
        val user = userService.getUser(userId)
        return scrapRepository.findByScrapedUserOrderByIdDesc(user).map { ScrapResponse.from(it) }
    }

    /**
     * [id]에 해당하는 스크랩 조회
     */
    fun getScrap(id: Long): ScrapResponse {
        return ScrapResponse.from(getScrapEntity(id))
    }

    /**
     * 스크랩 생성
     */
    @Transactional
    fun createScrap(request: CreateScrapRequest) {
        val metadata = metadataReader.read(request.url)
        val newScrap = Scrap.createScrap(
            url = metadata.url,
            title = metadata.title,
            description = metadata.description,
            image = metadata.imageUrl,
            scrapedUser = userService.getUser(request.userId)
        )
        scrapRepository.save(newScrap)
    }

    /**
     * [id]에 해당하는 스크랩 삭제
     */
    @Transactional
    fun deleteScrap(id: Long) {
        scrapRepository.delete(getScrapEntity(id))
    }

    private fun getScrapEntity(id: Long) : Scrap {
        return scrapRepository.findById(id) ?: throw ScrapNotFoundException().addAttribute("id", id)
    }

}