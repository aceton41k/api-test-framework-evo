package com.example.api.evo.models.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetMenuResponse extends BaseResponse<List<GetMenuResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("_id")
        private String id;

        private String url;
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

        private List<Category> category;
        private Boolean maintenance;

        @JsonProperty("in_favorites")
        private Boolean inFavorites;

        @JsonProperty("priority_favorite")
        private Integer priorityFavorite;

        @JsonProperty("web_enabled")
        private Boolean webEnabled;

        @JsonProperty("navigation_type")
        private String navigationType;

        @JsonProperty("withoutAuth")
        private Boolean withoutAuth;

        private String icon;
        private String title;
        private String description;
        private String background;
        private Boolean hasBnplCards;

        @JsonProperty("available_regions")
        private List<String> availableRegions;
    }

    @Getter
    public static class Category {
        private String id;
        private Integer priority;
    }

    @Getter
    public static class Version {
        private String ios;
        private String android;
    }
}
