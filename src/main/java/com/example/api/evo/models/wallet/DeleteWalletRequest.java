package com.example.api.evo.models.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class DeleteWalletRequest extends BaseRequest<DeleteWalletRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("account_id")
        private Long accountId;
    }
}
