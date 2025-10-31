package com.example.api.evo.models.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class CreateWalletResponse extends BaseResponse<CreateWalletResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("card_number")
        private String cardNumber;

        @JsonProperty("account_id")
        private Integer accountId;
    }
}
