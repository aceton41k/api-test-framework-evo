package com.example.api.evo.models.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class CardBtnManageResponse extends BaseResponse<CardBtnManageResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("uzcard_humo")
        private Boolean uzcardHumo;

        @JsonProperty("order_card")
        private Boolean orderCard;

        private Boolean atto;

        @JsonProperty("visa_kapital")
        private Boolean visaKapital;

        @JsonProperty("visa_capital")
        private Boolean visaCapital;
    }
}
