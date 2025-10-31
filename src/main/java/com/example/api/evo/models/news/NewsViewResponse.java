package com.example.api.evo.models.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class NewsViewResponse extends BaseResponse<NewsViewResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("news_id")
        private Integer newsId;

        @JsonProperty("views_count")
        private Integer viewsCount;
    }
}
