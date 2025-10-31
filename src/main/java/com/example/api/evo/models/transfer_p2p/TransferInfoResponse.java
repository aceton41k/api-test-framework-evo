package com.example.api.evo.models.transfer_p2p;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class TransferInfoResponse extends BaseResponse<TransferInfoResponse.Result> {
    @Getter
    public static class Result {
        private Double percent;

        @JsonProperty("receive_min_limit")
        private Long receiveMinLimit;

        @JsonProperty("receive_max_limit")
        private Long receiveMaxLimit;

        @JsonProperty("send_min_limit")
        private Long sendMinLimit;

        @JsonProperty("send_max_limit")
        private Long sendMaxLimit;

        @JsonProperty("receive_currency")
        private String receiveCurrency;

        @JsonProperty("send_currency")
        private String sendCurrency;

        @JsonProperty("currency_rate")
        private Double currencyRate;

        @JsonProperty("currency_reverse")
        private Boolean currencyReverse;

        @JsonProperty("currency_rate_text")
        private String currencyRateText;

        @JsonProperty("transaction_currency")
        private String transactionCurrency;

        @JsonProperty("cashback_percent")
        private Double cashbackPercent;

        @JsonProperty("premium_percent")
        private Double premiumPercent;

        @JsonProperty("premium_limit")
        private Long premiumLimit;

        @JsonProperty("receive_card_type")
        private String receiveCardType;

        @JsonProperty("receive_card_number")
        private String receiveCardNumber;

        @JsonProperty("premium_commission_percent")
        private Double premiumCommissionPercent;
    }
}
