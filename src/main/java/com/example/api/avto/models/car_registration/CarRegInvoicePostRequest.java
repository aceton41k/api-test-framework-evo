package com.example.api.avto.models.car_registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class CarRegInvoicePostRequest {

    @Getter
    @Builder
    public static class Params {
        @JsonProperty("sms")
        private String sms;
    }
}
