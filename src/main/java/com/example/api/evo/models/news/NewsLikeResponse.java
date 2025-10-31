package com.example.api.evo.models.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class NewsLikeResponse extends BaseResponse<NewsLikeResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("news_id")
        private Integer newsId;

        @JsonProperty("likes_count")
        private String likesCount;
    }
}
