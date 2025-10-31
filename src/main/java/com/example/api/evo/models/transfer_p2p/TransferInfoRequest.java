package com.example.api.evo.models.transfer_p2p;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TransferInfoRequest extends BaseRequest<TransferInfoRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("account_id")
        private Long accountId;

        @JsonProperty("card_number_hash")
        private String cardNumberHash;
    }
}
