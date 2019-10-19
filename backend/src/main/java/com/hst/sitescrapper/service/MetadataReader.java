package com.hst.sitescrapper.service;

import com.hst.sitescrapper.model.response.MetaDataResponse;
import com.hst.sitescrapper.type.MetadataType;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class MetadataReader {

    private Logger log = LoggerFactory.getLogger(MetadataReader.class);

    private Pattern URL_PATTERN = Pattern.compile("/[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b" +
            "([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)?/gi");

    public MetaDataResponse read(String requestUrl) throws IOException {
        if (StringUtils.isEmpty(requestUrl) || !URL_PATTERN.matcher(requestUrl).matches()) {
            throw new IllegalArgumentException("request url is invalid");
        }

        Document document = getWebsiteDocument(requestUrl);
        Elements metaTags = document.getElementsByTag("meta");
        Map<String, String> ogMeta = new HashMap<>();

        for (Element element : metaTags) {
            String property = element.attr("property");

            if (MetadataType.contains(property)) {
                String content = element.attr("content");
                log.info("Collect meta data property=[{}], content=[{}]", property, content);
                ogMeta.put(property, content);
            }
        }

        log.info("{} meta data was collected.", ogMeta.size());

        if (ogMeta.keySet().isEmpty()) {
            ogMeta.putAll(additionalReading(document));

            throw new IOException("Cannot read open graph meta data");
        }

        return MetaDataResponse.of(ogMeta);
    }

    private Document getWebsiteDocument(String requestUrl) throws IOException {
        return Jsoup.connect(requestUrl).get();
    }

    private Map<String, String> additionalReading(Document document) {
        Map<String, String> additionalMetaData = new HashMap<>();

        Elements titleTags = document.getElementsByTag("title");
        String title = null;
        for (Element element : titleTags) {
            additionalMetaData.put("title", element.val());
        }
        return additionalMetaData;
    }

}
