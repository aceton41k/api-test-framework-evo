package com.example.api.merchant.models.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetInvoiceStatusResponse {
    @JsonProperty("error_code")
    private Integer errorCode;

    @JsonProperty("error_note")
    private String errorNote;

    private Integer status;

    @JsonProperty("status_note")
    private String statusNote;
    @JsonProperty("payment_id")
    private String paymentId;
}
