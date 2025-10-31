package com.example.api.evo.models.wallet;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class WalletRatesResponse extends BaseResponse<List<WalletRatesResponse.Result>> {
    @Getter
    public static class Result {
        private String code;
        private String type;

        @JsonProperty("refill_comission")
        private Double refillComission;

        @JsonProperty("wallet2wallet_comission")
        private Double wallet2walletComission;

        @JsonProperty("wallet2card_comission")
        private Double wallet2cardComission;

        @JsonProperty("month_limit")
        private Long monthLimit;

        @JsonProperty("month_limit_brv")
        private Double monthLimitBrv;

        @JsonProperty("usage_limit")
        private Long usageLimit;

        @JsonProperty("usage_limit_brv")
        private Double usageLimitBrv;

        @JsonProperty("delete_limit")
        private Integer deleteLimit;

        @JsonProperty("month_limit_text")
        private String monthLimitText;

        @JsonProperty("usage_limit_text")
        private String usageLimitText;
    }
}
