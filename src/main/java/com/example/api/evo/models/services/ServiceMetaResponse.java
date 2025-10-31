package com.example.api.evo.models.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class ServiceMetaResponse extends BaseResponse<ServiceMetaResponse.Result> {
    @Getter
    public static class Result {
        private Cashback cashback;

        @JsonProperty("cashback_settings")
        private List<CashbackSetting> cashbackSettings;

        private Confirmation confirmation;

        @Getter
        public static class Cashback {
            private Double percent;
        }

        @Getter
        public static class CashbackSetting {
            private String type;

            @JsonProperty("is_click_card")
            private Boolean isClickCard;

            private Double percent;

            @JsonProperty("premium_cashback")
            private Boolean premiumCashback;
        }

        @Getter
        public static class Confirmation {
            @JsonProperty("confirm_text")
            private String confirmText;
        }
    }
}
