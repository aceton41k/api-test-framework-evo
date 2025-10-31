package com.example.api.merchant.models.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateInvoiceInvalidRequest {
    @JsonProperty("service_id")
    private Object serviceId;

    private Object amount;

    @JsonProperty("phone_number")
    private Object phoneNumber;

    @JsonProperty("merchant_trans_id")
    private Object merchantTransId;
}
