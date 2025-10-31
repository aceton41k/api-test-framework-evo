package com.example.api.evo.models.favorite;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class FavoriteListResponse extends BaseResponse<List<FavoriteListResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("service_id")
        private Integer serviceId;

        private Long datetime;
        private String name;
        private Double amount;
        private String value;
        private ServiceDetails service;
        private String image;
        private String id;
    }

    @Getter
    public static class ServiceDetails {
        private String name;

        @JsonProperty("category_id")
        private Integer categoryId;

        private Integer status;

        @JsonProperty("min_limit")
        private Double minLimit;

        @JsonProperty("max_limit")
        private Double maxLimit;

        @JsonProperty("commission_percent")
        private Double commissionPercent;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        private Boolean maintenance;

        @JsonProperty("direct_payment")
        private Boolean directPayment;

        @JsonProperty("favorite_permission")
        private Boolean favoritePermission;

        @JsonProperty("myhome_permission")
        private Boolean myhomePermission;

        @JsonProperty("cashback_label")
        private String cashbackLabel;

        private String label;

        @JsonProperty("qr_only")
        private Boolean qrOnly;
    }
}
