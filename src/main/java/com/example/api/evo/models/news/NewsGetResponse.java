package com.example.api.evo.models.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class NewsGetResponse extends BaseResponse<NewsGetResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("news_id")
        private Integer newsId;

        @JsonProperty("news_title")
        private String newsTitle;

        @JsonProperty("news_content_short")
        private String newsContentShort;

        @JsonProperty("news_content")
        private String newsContent;

        @JsonProperty("news_image")
        private String newsImage;

        @JsonProperty("news_thumbnail")
        private String newsThumbnail;

        @JsonProperty("promo_image")
        private String promoImage;

        private String url;

        @JsonProperty("url_text")
        private String urlText;

        private String action;
        private Long datetime;

        @JsonProperty("views_count")
        private Integer viewsCount;

        @JsonProperty("likes_count")
        private Integer likesCount;

        @JsonProperty("like_status")
        private Integer likeStatus;

        private String style;
        private String type;
    }
}
