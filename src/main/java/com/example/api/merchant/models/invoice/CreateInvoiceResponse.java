package com.example.api.merchant.models.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class CreateInvoiceResponse {
    @JsonProperty("error_code")
    private Integer errorCode;

    @JsonProperty("error_note")
    private String errorNote;

    @JsonProperty("invoice_id")
    private Integer invoiceId;

    @JsonProperty("eps_id")
    private String epsId;
}
