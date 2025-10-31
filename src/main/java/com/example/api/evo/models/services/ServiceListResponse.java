package com.example.api.evo.models.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class ServiceListResponse extends BaseResponse<List<ServiceListResponse.Result>> {
    @Getter
    public static class Result {
        private Long id;
        private String name;

        @JsonProperty("category_id")
        private Integer categoryId;

        private Integer status;
        private Integer priority;
        private Boolean visible;
        private String image;

        @JsonProperty("min_limit")
        private Integer minLimit;

        @JsonProperty("max_limit")
        private Integer maxLimit;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        @JsonProperty("offline_available")
        private Boolean offlineAvailable;

        private Boolean maintenance;

        @JsonProperty("autopay_schedule_available")
        private Boolean autopayScheduleAvailable;

        @JsonProperty("autopay_event_available")
        private Boolean autopayEventAvailable;

        @JsonProperty("favorite_permission")
        private Boolean favoritePermission;

        @JsonProperty("myhome_permission")
        private Boolean myhomePermission;

        @JsonProperty("web_view_url")
        private String webViewUrl;

        @JsonProperty("qr_only")
        private Boolean qrOnly;

        private Integer version;

        @JsonProperty("api_version")
        private Integer apiVersion;

        @JsonProperty("short_name")
        private String shortName;

        private String icon;
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
