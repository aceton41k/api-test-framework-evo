package com.example.api.evo.models.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;
import com.example.api.evo.models.Location;

import java.util.List;

@Getter
public class ServiceSearchResponse extends BaseResponse<ServiceSearchResponse.Result> {

    @Getter
    public static class Result {
        private List<Service> services;
        private List<Indoor> indoors;

        @JsonProperty("menu_services")
        private List<MenuService> menuServices;

        private List<Service> popular;
        private Card card;
    }

    @Getter
    public static class Service {
        private Integer id;
        private String name;
        private String trademark;

        @JsonProperty("category_id")
        private Integer categoryId;

        private Integer status;
        private Integer priority;
        private Boolean visible;
        private String type;
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
        private String myhomePermission;

        @JsonProperty("web_view_url")
        private String webViewUrl;

        @JsonProperty("qr_only")
        private Boolean qrOnly;

        private String label;
        private String keywords;
        private String lang;
        private String address;

        @JsonProperty("commission_percent")
        private Double commissionPercent;

        @JsonProperty("cashback_label")
        private String cashbackLabel;

        private List<String> keyword;
        private Integer version;

        @JsonProperty("api_version")
        private Integer apiVersion;

        private Location location;
    }

    @Getter
    public static class Indoor {
        private Integer id;
        private String name;
        private String trademark;

        @JsonProperty("category_id")
        private Integer categoryId;

        private Integer status;
        private Integer priority;
        private Boolean visible;
        private String type;
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
        private String myhomePermission;

        @JsonProperty("web_view_url")
        private String webViewUrl;

        @JsonProperty("qr_only")
        private Boolean qrOnly;

        private String label;
        private String keywords;
        private String lang;
        private String address;

        @JsonProperty("commission_percent")
        private Double commissionPercent;

        @JsonProperty("cashback_label")
        private String cashbackLabel;

        private List<String> keyword;
        private Integer version;

        @JsonProperty("api_version")
        private Integer apiVersion;

        private Location location;
        private Integer distance;
    }

    @Getter
    public static class MenuService {
        @JsonProperty("_id")
        private String id;

        private String url;
        private String label;

        @JsonProperty("event_id")
        private String eventId;

        @JsonProperty("identification_required")
        private Boolean identificationRequired;

        private Integer version;

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

        private Boolean withoutAuth;
        private String title;
        private String description;

        @JsonProperty("api_version")
        private Integer apiVersion;
    }

    @Getter
    public static class Category {
        private String id;
        private Integer priority;
    }

    @Getter
    public static class Card {
    }
}
