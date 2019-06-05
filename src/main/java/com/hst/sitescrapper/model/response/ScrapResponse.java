package com.hst.sitescrapper.model.response;

import com.hst.sitescrapper.model.entity.Scrap;

import java.util.Date;

public class ScrapResponse {
    private String id;
    private String url;
    private Date createAt;
    private String title;
    private String image;
    private String description;

    private ScrapResponse(String id, String url, Date createAt, String title, String image, String description) {
        this.id = id;
        this.url = url;
        this.createAt = createAt;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public static ScrapResponse of(Scrap scrap) {
        return new ScrapResponse(
                scrap.getId().toString(),
                scrap.getUrl(),
                scrap.getCreateAt(),
                scrap.getTitle(),
                scrap.getImage(),
                scrap.getDescription()
        );
    }

}
