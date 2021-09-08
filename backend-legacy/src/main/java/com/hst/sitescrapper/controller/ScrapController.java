package com.hst.sitescrapper.controller;

import com.hst.sitescrapper.model.api.ApiResponse;
import com.hst.sitescrapper.model.request.ScrapRequest;
import com.hst.sitescrapper.service.ScrapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scrap")
public class ScrapController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ScrapService scrapService;

    @GetMapping
    public ApiResponse findWebSlices() {
        return ApiResponse.ok(scrapService.findScraps());
    }

    @PostMapping
    public ApiResponse createWebSlice(@RequestBody ScrapRequest scrapRequest) {
        log.info("New scrap request {}", scrapRequest);

        scrapService.createScrap(scrapRequest);

        return ApiResponse.ok();
    }



}
