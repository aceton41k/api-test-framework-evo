package com.example.api.evo.models.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class ServiceDataResponse extends BaseResponse<ServiceDataResponse.Result> {
    @Getter
    public static class Result {
        private Integer id;
        private String name;

        @JsonProperty("category_id")
        private Integer categoryId;

        private Integer status;
        private String image;

        @JsonProperty("min_limit")
        private Integer minLimit;

        @JsonProperty("max_limit")
        private Integer maxLimit;

        private String type;
        private Double lat;

        @JsonProperty("long") // Указывает, что JSON-ключ "long" маппится на это поле
        private Double longitude;

        private String address;

        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        private Boolean maintenance;

        @JsonProperty("direct_payment")
        private Boolean directPayment;

        @JsonProperty("commission_percent")
        private Double commissionPercent;

        @JsonProperty("favorite_permission")
        private Boolean favoritePermission;

        @JsonProperty("myhome_permission")
        private Boolean myhomePermission;

        @JsonProperty("cashback_label")
        private String cashbackLabel;

        private String label;

        @JsonProperty("qr_only")
        private Boolean qrOnly;

        @JsonProperty("api_version")
        private Integer apiVersion;

        private Integer version;
    }
}
