package com.hst.sitescrapper.model.request;

public class ScrapRequest {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ScrapRequest{" +
                "url='" + url + '\'' +
                '}';
    }
}
