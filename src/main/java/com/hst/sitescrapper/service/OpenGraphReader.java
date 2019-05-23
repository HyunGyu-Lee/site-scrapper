package com.hst.sitescrapper.service;

import com.hst.sitescrapper.model.response.OpenGraphMetaDataResponse;
import com.hst.sitescrapper.type.MetadataType;
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

@Component
public class OpenGraphReader {

    private Logger log = LoggerFactory.getLogger(OpenGraphReader.class);

    public OpenGraphMetaDataResponse read(String requestUrl) throws IOException {
        Document document = Jsoup.connect(requestUrl).get();
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

        return OpenGraphMetaDataResponse.of(ogMeta);
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
