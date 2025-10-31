package com.example.api.evo.models.indoor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;
import com.example.api.evo.models.Location;

import lombok.Getter;

public class IndoorServiceListResponse extends BaseResponse<List<IndoorServiceListResponse.ServiceResult>> {
    @Getter
    public static class ServiceResult {
        private Integer id;
        private String name;

        @JsonProperty("service_name")
        private String serviceName;

        @JsonProperty("category_id")
        private Integer categoryId;

        private Integer status;
        private Integer priority;
        private Boolean visible;
        private String image;

        @JsonProperty("banner_image")
        private String bannerImage;

        @JsonProperty("min_limit")
        private Integer minLimit;

        @JsonProperty("service_category")
        private Integer serviceCategory;

        @JsonProperty("max_limit")
        private Integer maxLimit;

        @JsonProperty("commission_percent")
        private Integer commissionPercent;

        private String address;

        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        @JsonProperty("offline_available")
        private Boolean offlineAvailable;

        private Boolean maintenance;

        @JsonProperty("autopay_schedule_available")
        private Boolean autopayScheduleAvailable;

        @JsonProperty("favorite_permission")
        private Boolean favoritePermission;

        private String label;

        @JsonProperty("web_view_url")
        private String webViewUrl;

        @JsonProperty("cashback_label")
        private String cashbackLabel;

        @JsonProperty("cashback_percent")
        private Double cashbackPercent;

        @JsonProperty("cashback_expire_date")
        private String cashbackExpireDate;

        @JsonProperty("qr_only")
        private Boolean qrOnly;

        private String type;
        private Double distance;

        @JsonProperty("direct_payment")
        private Boolean directPayment;

        private String version;

        @JsonProperty("api_version")
        private String apiVersion;

        private String advertisement;
        private Location location;
    }
}
