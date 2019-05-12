package com.hst.sitescrapper.controller;

import com.hst.sitescrapper.model.api.ApiResponse;
import com.hst.sitescrapper.model.request.ScrapRequest;
import com.hst.sitescrapper.service.ScrapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("scrap")
public class ScrapController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ScrapService scrapService;

    @GetMapping
    public ResponseEntity<ApiResponse> findWebSlices() {
        return new ResponseEntity<>(ApiResponse.of(100, "success", scrapService.findWebSlices()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createWebSlice(@RequestBody ScrapRequest scrapRequest) {
        log.info("New scrap request {}", scrapRequest);

        scrapService.createWebSlice(scrapRequest);

        return new ResponseEntity<>(ApiResponse.of(100, "success"), HttpStatus.OK);
    }



}
