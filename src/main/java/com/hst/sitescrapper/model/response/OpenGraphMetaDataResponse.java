package com.hst.sitescrapper.model.response;

import com.hst.sitescrapper.type.MetadataType;

import java.util.Map;

public class OpenGraphMetaDataResponse {

    private String title;
    private String url;
    private String imageUrl;
    private String description;

    // todo add open graph meta data type!

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OpenGraphMetaDataResponse{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static OpenGraphMetaDataResponse of(Map<String, String> ogMeta) {
        OpenGraphMetaDataResponse response = new OpenGraphMetaDataResponse();
        response.setTitle(ogMeta.get(MetadataType.OG_TITLE.getMetaTypeName()));
        response.setImageUrl(ogMeta.get(MetadataType.OG_IMAGE.getMetaTypeName()));
        response.setUrl(ogMeta.get(MetadataType.OG_URL.getMetaTypeName()));
        response.setDescription(ogMeta.get(MetadataType.OG_DESCRIPTION.getMetaTypeName()));
        return response;
    }
}
