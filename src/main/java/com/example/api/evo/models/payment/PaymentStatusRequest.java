package com.example.api.evo.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class PaymentStatusRequest extends BaseRequest<PaymentStatusRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("payment_id")
        private Long paymentId;
    }
}
