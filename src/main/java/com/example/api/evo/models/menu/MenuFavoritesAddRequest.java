package com.example.api.evo.models.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MenuFavoritesAddRequest extends BaseRequest<MenuFavoritesAddRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("nav_ids")
        private List<String> navIds;
    }
}
