package com.hst.sitescrapper;

import com.hst.sitescrapper.model.request.ScrapRequest;
import com.hst.sitescrapper.model.response.MetaDataResponse;
import com.hst.sitescrapper.repository.ScrapRepository;
import com.hst.sitescrapper.service.MetadataReader;
import com.hst.sitescrapper.service.ScrapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ScrapEntityServiceTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Mock
    private ScrapRepository webSliceRepository;

    @Mock
    private MetadataReader metadataReader;

    @InjectMocks
    private ScrapService scrapService = new ScrapService(webSliceRepository, metadataReader);

    private String MOCK_URL = "https://www.naver.com";

    @Test
    public void ogTagParseTest() {
        String ogTagRegex = "<meta property=\"og:(.+)\" content=\"(.+)\"/?>";

        Pattern pattern = Pattern.compile(ogTagRegex);

        Matcher matcher = pattern.matcher("<meta property=\"og:title\" content=\"네이버\">");

        assertTrue(matcher.find());
        assertEquals("title", matcher.group(1));
        assertEquals("네이버", matcher.group(2));
    }

    @Test
    public void createWebSliceTest() throws Exception {
        // given
        ScrapRequest request = mock(ScrapRequest.class);
        request.setUrl(MOCK_URL);

        when(metadataReader.read(request.getUrl())).thenReturn(new MetaDataResponse());

        // when
        scrapService.createScrap(request);

        // then
        verify(webSliceRepository).save(any());
    }

}
