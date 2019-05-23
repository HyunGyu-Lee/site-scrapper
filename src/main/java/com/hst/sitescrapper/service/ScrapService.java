package com.hst.sitescrapper.service;

import com.hst.sitescrapper.exception.ServiceException;
import com.hst.sitescrapper.model.entity.Scrap;
import com.hst.sitescrapper.model.request.ScrapRequest;
import com.hst.sitescrapper.model.response.OpenGraphMetaDataResponse;
import com.hst.sitescrapper.model.response.ScrapResponse;
import com.hst.sitescrapper.repository.ScrapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ScrapService {

    private ScrapRepository scrapRepository;
    private OpenGraphReader openGraphReader;

    @Autowired
    public ScrapService(ScrapRepository scrapRepository, OpenGraphReader openGraphReader) {
        this.scrapRepository = scrapRepository;
        this.openGraphReader = openGraphReader;
    }

    private Logger log = LoggerFactory.getLogger(getClass());

    /***
     * 슬라이스 목록 조회
     *
     * @return 슬라이스 목록
     */
    public List<ScrapResponse> findScraps() {
        return scrapRepository.findAll().stream().map(e -> {
            ScrapResponse res = new ScrapResponse();
            res.setCreateAt(e.getCreateAt());
            res.setId(e.getId().toString());
            res.setUrl(e.getUrl());
            res.setTitle(e.getTitle());
            res.setImage(e.getImage());
            res.setDescription(e.getDescription());
            return res;
        }).collect(Collectors.toList());
    }

    /***
     * 슬라이스 등록
     *
     * @param scrapRequest 등록 요청
     */
    public void createScrap(ScrapRequest scrapRequest) {
        // Todo Check Request is valid

        String scrapUrl = scrapRequest.getUrl();
        OpenGraphMetaDataResponse openGraphMetaDataResponse;

        try {
            openGraphMetaDataResponse = openGraphReader.read(scrapUrl);
            log.info("{}", openGraphMetaDataResponse);
        } catch (IOException e) {
            log.error("open graph meta data read fail", e);
            throw new ServiceException("웹 사이트 메타데이터 분석에 실패했습니다.");
        }

        // Create entity
        Scrap scrap = new Scrap();
        scrap.setId(UUID.randomUUID());
        scrap.setUrl(scrapUrl);
        scrap.setCreateAt(new Date());
        scrap.setTitle(openGraphMetaDataResponse.getTitle());
        scrap.setImage(openGraphMetaDataResponse.getImageUrl());
        scrap.setDescription(openGraphMetaDataResponse.getDescription());

        scrapRepository.save(scrap);
    }

}
