package com.hst.sitescrapper.service;

import com.hst.sitescrapper.model.OpenGraphMetaType;
import com.hst.sitescrapper.model.response.OpenGraphMetaDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OpenGraphReader {

    private Logger log = LoggerFactory.getLogger(OpenGraphReader.class);

    public OpenGraphMetaDataResponse read(String requestUrl) throws IOException {
        BufferedReader br = null;
        Map<String, String> ogMata = new HashMap<>();
        Pattern ogTagPattern = Pattern.compile("<meta property=\"og:(.+)\" content=\"(.+)\".+$");

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                String line;
                while ((line = br.readLine()) != null) {
                    Matcher matcher = ogTagPattern.matcher(line);

                    if (matcher.find()) {
                        String tagName = matcher.group(1);
                        String tagValue = matcher.group(2);
                        if (OpenGraphMetaType.contains("og:" + tagName)) {
                            log.info("Collect open graph meta data {}", line);
                            ogMata.put(tagName, tagValue);
                        }
                    }
                }
            } else {
                // todo throw meaningful exception!!
                throw new IOException();
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(String.format("Not valid url. %s", requestUrl));
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("close fail. ", e);
                }
            }
        }

        return OpenGraphMetaDataResponse.of(ogMata);
    }
}
