package com.example.api.evo.models.loyalty_card;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class LoyaltyCardsEditRequest extends BaseRequest<LoyaltyCardsEditRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String id;
        private String title;
        private String code;

        @JsonProperty("partner_id")
        private String partnerId;
    }
}
