package com.hst.sitescrapper.model.response;

import com.hst.sitescrapper.model.entity.ScrapEntity;

import java.time.LocalDateTime;

public class ScrapResponse {
    private Long id;
    private String url;
    private LocalDateTime createdAt;
    private String title;
    private String image;
    private String description;

    private ScrapResponse(Long id, String url, LocalDateTime createdAt, String title, String image, String description) {
        this.id = id;
        this.url = url;
        this.createdAt = createdAt;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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

    public static ScrapResponse of(ScrapEntity scrap) {
        return new ScrapResponse(
                scrap.getId(),
                scrap.getUrl(),
                scrap.getCreatedAt(),
                scrap.getTitle(),
                scrap.getImage(),
                scrap.getDescription()
        );
    }

}
