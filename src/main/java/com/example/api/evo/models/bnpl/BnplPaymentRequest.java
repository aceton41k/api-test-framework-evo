package com.example.api.evo.models.bnpl;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class BnplPaymentRequest extends BaseRequest<BnplPaymentRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("service_id")
        private Integer serviceId;

        private PaymentParameters parameters;

        @JsonProperty("transaction_type")
        private String transactionType;

        private String partner;

        @Getter
        @Builder
        public static class PaymentParameters {

            @JsonProperty("phone_num")
            private String phoneNum;
            
            private Integer amount;
        }
    }
}
