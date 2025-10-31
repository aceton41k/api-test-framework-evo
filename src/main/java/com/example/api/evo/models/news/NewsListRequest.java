package com.example.api.evo.models.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class NewsListRequest extends BaseRequest<NewsListRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("page_number")
        @Builder.Default
        private Integer pageNumber = 1;

        @JsonProperty("page_size")
        @Builder.Default
        private Integer pageSize = 10;
    }
}
