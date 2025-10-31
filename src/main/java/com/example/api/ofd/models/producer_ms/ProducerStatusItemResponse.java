package com.example.api.ofd.models.producer_ms;

import lombok.Getter;

@Getter
public class ProducerStatusItemResponse {
    private Integer id;
    private Integer paydocId;
    private Integer serviceId;
    private String paymentInfo;
    private String mainStatus;
    private String commissionStatus;
    private String createdAt;
    private String updatedAt;
    private Boolean isRefund;
    private String mainErrorMessage;
    private String commissionErrorMessage;
}
