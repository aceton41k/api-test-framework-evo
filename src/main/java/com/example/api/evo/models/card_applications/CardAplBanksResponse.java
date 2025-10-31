package com.example.api.evo.models.card_applications;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class CardAplBanksResponse extends BaseResponse<List<CardAplBanksResponse.Result>> {
    @Getter
    public static class Result {
        private String code;
        private String name;
        private List<Design> designs;
        private String terms;

        @JsonProperty("payment_types")
        private List<String> paymentTypes;

        private Service service;
        private Boolean delivery;

        @JsonProperty("location_url")
        private String locationUrl;
    }

    @Getter
    public static class Design {
        private Integer design;
        private Integer price;

        @JsonProperty("delivery_price")
        private Integer deliveryPrice;

        private String url;
    }

    @Getter
    public static class Service {
        private Integer id;

        @JsonProperty("card_types")
        private List<String> cardTypes;
    }
}
