package com.hst.scrapper.scrap.ui

import com.hst.scrapper.global.model.api.ApiResponse
import com.hst.scrapper.scrap.domain.application.ScrapService
import com.hst.scrapper.scrap.ui.response.ScrapResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/scraps")
class ScrapController(
    val scrapService: ScrapService
) {

    @GetMapping
    fun getScraps(@RequestParam userId: Long): ApiResponse<List<ScrapResponse>> {
        return ApiResponse.success(scrapService.getScraps(userId))
    }

}