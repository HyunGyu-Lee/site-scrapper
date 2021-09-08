package com.hst.sitescrapper;

import com.hst.sitescrapper.model.response.MetaDataResponse;
import com.hst.sitescrapper.service.MetadataReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class MetadataReaderTest {

    private MetadataReader metadataReader = new MetadataReader();

    private String MOCK_HTML = "";

    @Test
    public void readOpenGraph() {
        try {
            MetaDataResponse openGraphMetaData = metadataReader.read("https://www.naver.com");

            assertEquals("네이버", openGraphMetaData.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
