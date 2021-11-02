package com.hst.scrapper.modules.scrap.ui

import com.hst.scrapper.global.model.api.ApiResponse
import com.hst.scrapper.modules.scrap.application.ScrapService
import com.hst.scrapper.modules.scrap.ui.request.CreateScrapRequest
import com.hst.scrapper.modules.scrap.ui.response.ScrapResponse
import org.springframework.web.bind.annotation.*

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

    @GetMapping("{id}")
    fun getScrap(@PathVariable id: Long): ApiResponse<ScrapResponse> {
        return ApiResponse.success(scrapService.getScrap(id))
    }

    @PostMapping
    fun createScrap(@RequestBody request: CreateScrapRequest): ApiResponse<Any?> {
        scrapService.createScrap(request)
        return ApiResponse.success()
    }

    @DeleteMapping("{id}")
    fun deleteScrap(@PathVariable id: Long): ApiResponse<Any?> {
        scrapService.deleteScrap(id)
        return ApiResponse.success()
    }

}