package com.example.api.evo.models.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetMenuGroupResponse extends BaseResponse<GetMenuGroupResponse.Result> {
    @Getter
    public static class Result {
        private String title;

        @JsonProperty("sub_navigations")
        private List<SubNavigation> subNavigations;
    }

    @Getter
    public static class SubNavigation {
        @JsonProperty("_id")
        private String id;

        private String url;
        private String label;

        @JsonProperty("event_id")
        private String eventId;

        @JsonProperty("identification_required")
        private Boolean identificationRequired;

        private String version;

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

        private String icon;
        private String title;
        private String description;
    }

    @Getter
    public static class Category {
        private String id;
        private Integer priority;
    }
}
