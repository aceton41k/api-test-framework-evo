package com.example.api.evo.models.myhome;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MyHomeFromPaymentRequest extends BaseRequest<MyHomeFromPaymentRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("payment_id")
        private Long paymentId;

        @JsonProperty("myhome_id")
        private Long myhomeId;
    }
}
