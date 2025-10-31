package com.example.api.evo.models.myhome;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class MyHomePaymentInfoResponse extends BaseResponse<MyHomePaymentInfoResponse.Result> {
    @Getter
    public static class Result {
        private List<Info> info;
        private Service service;
    }

    @Getter
    public static class Info {
        private String label;
        private String name;
        private String value;
    }

    @Getter
    public static class Service {
        private Integer id;

        @JsonProperty("click_service_id")
        private Integer clickServiceId;

        private String name;
        private String trademark;

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

        @JsonProperty("commission_percent")
        private Integer commissionPercent;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        private Boolean maintenance;

        @JsonProperty("direct_payment")
        private Boolean direct_payment;

        @JsonProperty("offline_available")
        private Boolean offlineAvailable;

        @JsonProperty("autopay_schedule_available")
        private Boolean autopayScheduleAvailable;

        @JsonProperty("autopay_event_available")
        private Boolean autopayEventAvailable;

        @JsonProperty("favorite_permission")
        private Boolean favoritePermission;

        @JsonProperty("myhome_permission")
        private Boolean myhomePermission;

        @JsonProperty("cashback_label")
        private String cashbackLabel;

        private String label;

        @JsonProperty("web_view_url")
        private String webViewUrl;

        @JsonProperty("qr_only")
        private Boolean qrOnly;

        private String keywords;
        private String lang;

        @JsonProperty("updated_at")
        private String updatedAt;

        @JsonProperty("created_at")
        private String createdAt;
    }
}
