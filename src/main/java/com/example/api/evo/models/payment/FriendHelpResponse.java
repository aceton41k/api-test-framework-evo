package com.example.api.evo.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class FriendHelpResponse extends BaseResponse<FriendHelpResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("invoice_id")
        private Long invoiceId;
    }
}
