package com.example.api.merchant.models.invoice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateInvoiceRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("service_id")
    private Integer serviceId;

    private Float amount;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("merchant_trans_id")
    private String merchantTransId;
}
