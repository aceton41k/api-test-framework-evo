package com.example.api.evo.models.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetMenuCategoriesResponse extends BaseResponse<List<GetMenuCategoriesResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("_id")
        private String id;

        private Integer priority;
        private String name;
    }
}
