package com.hst.sitescrapper.service;

import com.hst.sitescrapper.exception.ServiceException;
import com.hst.sitescrapper.model.entity.ScrapEntity;
import com.hst.sitescrapper.model.request.ScrapRequest;
import com.hst.sitescrapper.model.response.MetaDataResponse;
import com.hst.sitescrapper.model.response.ScrapResponse;
import com.hst.sitescrapper.repository.ScrapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ScrapService {

    private ScrapRepository scrapRepository;
    private MetadataReader metadataReader;

    @Autowired
    public ScrapService(ScrapRepository scrapRepository, MetadataReader metadataReader) {
        this.scrapRepository = scrapRepository;
        this.metadataReader = metadataReader;
    }

    private Logger log = LoggerFactory.getLogger(getClass());

    /***
     * 슬라이스 목록 조회
     *
     * @return 슬라이스 목록
     */
    public List<ScrapResponse> findScraps() {
        return scrapRepository.findAll().stream().map(ScrapResponse::of).collect(Collectors.toList());
    }

    /***
     * 슬라이스 등록
     *
     * @param scrapRequest 등록 요청
     */
    public void createScrap(ScrapRequest scrapRequest) {
        // Todo Check Request is valid

        String scrapUrl = scrapRequest.getUrl();
        MetaDataResponse metaDataResponse;

        // todo MetadataReader
        try {
            metaDataResponse = metadataReader.read(scrapUrl);
            log.info("{}", metaDataResponse);
        } catch (IOException e) {
            log.error("open graph meta data read fail", e);
            throw new ServiceException("웹 사이트 메타데이터 분석에 실패했습니다.");
        }

        // Create entity
        ScrapEntity scrap = new ScrapEntity();
        scrap.setUrl(scrapUrl);
        scrap.setTitle(metaDataResponse.getTitle());
        scrap.setImage(metaDataResponse.getImageUrl());
        scrap.setDescription(metaDataResponse.getDescription());

        scrapRepository.save(scrap);
    }

}
