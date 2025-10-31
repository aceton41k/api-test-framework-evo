package com.example.api.ofd.models.producer_ms;

import lombok.Getter;

import java.util.List;

@Getter
public class ProducerFiscallingStatusItemResponse {
    private List<ReceiptItem> responseBody;
    private String errorMessage;
    private Integer statusCode;
    private String dateTime;

    @Getter
    public static class ReceiptItem {
        private Integer paydocId;
        private Long receiptId;
        private Integer serviceId;
        private Boolean main;
        private Integer receiptType;
        private String paymentType;
        private String description;
        private String operationTime;
        private String status;
        private String qrCodeUrl;
    }
}
