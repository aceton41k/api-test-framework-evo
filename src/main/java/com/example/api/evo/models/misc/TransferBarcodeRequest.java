package com.example.api.evo.models.misc;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TransferBarcodeRequest extends BaseRequest<TransferBarcodeRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("account_id")
        private Long accountId;
    }
}
