package com.example.api.evo.models.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetMenuButtonsResponse extends BaseResponse<List<GetMenuButtonsResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("nav_id")
        private String navId;

        @JsonProperty("btn_titles")
        private List<String> btnTitles;
    }
}
