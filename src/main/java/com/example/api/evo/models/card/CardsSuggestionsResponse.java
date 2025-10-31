package com.example.api.evo.models.card;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class CardsSuggestionsResponse extends BaseResponse<List<CardsSuggestionsResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("card_number_space")
        private String cardNumberSpace;

        @JsonProperty("card_number")
        private String cardNumber;

        @JsonProperty("min_limit")
        private Integer minLimit;

        @JsonProperty("max_limit")
        private Integer maxLimit;

        @JsonProperty("receipt_max_limit")
        private Integer receiptMaxLimit;

        @JsonProperty("send_once_max_limit")
        private Integer sendOnceMaxLimit;

        @JsonProperty("receipt_once_max_limit")
        private Integer receiptOnceMaxLimit;

        private Double percent;

        @JsonProperty("bank_code")
        private String bankCode;

        private String name;

        @JsonProperty("bank_name")
        private String bankName;

        private String logo;

        @JsonProperty("background_url")
        private String backgroundUrl;

        @JsonProperty("logo_url")
        private String logoUrl;

        @JsonProperty("logo_cardtype")
        private String logoCardtype;

        @JsonProperty("card_type")
        private String cardType;

        @JsonProperty("font_color")
        private String fontColor;

        private String expiry;
    }
}
