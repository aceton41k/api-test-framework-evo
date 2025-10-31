package com.example.api.evo.models.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CardChangeNameRequest extends BaseRequest<CardChangeNameRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("account_id")
        private Long accountId;

        @JsonProperty("card_name")
        private String cardName;
    }
}
