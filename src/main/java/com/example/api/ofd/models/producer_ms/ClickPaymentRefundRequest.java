package com.example.api.ofd.models.producer_ms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClickPaymentRefundRequest {
    @JsonProperty("paydoc_id")
    private Integer paydocId;

    private Integer refund;

    @JsonProperty("service_id")
    @Builder.Default
    private Integer serviceId = 112;

    @JsonProperty("instant_refund")
    private Integer instantRefund;
}
