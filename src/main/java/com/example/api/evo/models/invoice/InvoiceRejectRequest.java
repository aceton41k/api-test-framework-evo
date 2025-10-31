package com.example.api.evo.models.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class InvoiceRejectRequest extends BaseRequest<InvoiceRejectRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("invoice_id")
        private Long invoiceId;
    }
}
