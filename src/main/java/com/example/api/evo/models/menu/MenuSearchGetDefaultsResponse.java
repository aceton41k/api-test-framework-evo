package com.example.api.evo.models.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class MenuSearchGetDefaultsResponse extends BaseResponse<List<MenuSearchGetDefaultsResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("category_title")
        private String categoryTitle;

        private List<Service> services;
    }

    @Getter
    public static class Service {
        @JsonProperty("_id")
        private String id;

        private String url;
        private String icon;
        private String label;

        @JsonProperty("event_id")
        private String eventId;

        @JsonProperty("identification_required")
        private Boolean identificationRequired;

        private Version version;

        @JsonProperty("antifraud_check")
        private Boolean antifraudCheck;

        @JsonProperty("analytics_variable")
        private String analyticsVariable;

        private List<CategoryMeta> category;
        private Boolean maintenance;

        @JsonProperty("in_favorites")
        private Boolean inFavorites;

        @JsonProperty("priority_favorite")
        private Integer priorityFavorite;

        private Boolean withoutAuth;
        private String background;
        private String title;
        private String description;

        @JsonProperty("navigation_type")
        private String navigationType;
    }

    @Getter
    public static class CategoryMeta {
        private String id;
        private Integer priority;
    }

    @Getter
    public static class Version {
        private String ios;
        private String android;
    }
}
