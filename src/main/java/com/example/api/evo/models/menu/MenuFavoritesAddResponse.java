package com.example.api.evo.models.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class MenuFavoritesAddResponse extends BaseResponse<List<MenuFavoritesAddResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("nav_id")
        private String navId;

        private Integer priority;
    }
}
