package com.example.api.avto.models.car_registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class CarRegInvoiceStatusPostRequest {

    @Getter
    @Builder
    public static class Params {
        @JsonProperty("invoice_ids")
        private List<Integer> invoiceIds;
    }
}
