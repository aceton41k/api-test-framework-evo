package com.example.api.evo.models.loyalty_card;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class LoyaltyCardsListResponse extends BaseResponse<List<LoyaltyCardsListResponse.Result>> {
    @Getter
    public static class Result {
        private String id;
        private String title;
        private String code;

        @JsonProperty("card_number")
        private String cardNumber;

        private String partner;

        @JsonProperty("partner_id")
        private String partnerId;

        private String logo;
        private String icon;
        private String amount;
    }
}
