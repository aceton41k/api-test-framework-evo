package com.example.api.evo.models.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class CategoryListResponse extends BaseResponse<List<CategoryListResponse.Result>> {
    @Getter
    public static class Result {
        private Long id;
        private String name;
        private Integer priority;
        private Integer status;
        private String image;
        private String topCategoryName;

        @JsonProperty("bg_image")
        private String bgImage;

        private String order;
        private Advertisement advertisement;
    }

    @Getter
    public static class Advertisement {
        private String url;
        private String image;

        @JsonProperty("analytics_variable")
        private String analyticsVariable;

        @JsonProperty("analytics_view_variable")
        private String analyticsViewVariable;
    }
}
