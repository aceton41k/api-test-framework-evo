package com.example.api.ofd.models.merchant_ms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QRCodeRequest {
    @JsonProperty("PaymentId")
    private Integer paymentId;

    @JsonProperty("QRCodeURL")
    private String qrCodeURL;
}
