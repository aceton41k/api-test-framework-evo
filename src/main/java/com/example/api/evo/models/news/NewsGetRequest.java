package com.example.api.evo.models.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class NewsGetRequest extends BaseRequest<NewsGetRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("news_id")
        private Integer newsId;
    }
}
