package com.example.api.evo.models.card_applications;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CardAplBanksRequest extends BaseRequest<CardAplBanksRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("card_type")
        private String cardType;

        private Integer region;
        private Integer city;
    }
}
