package com.example.api.evo.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class PaymentStatusResponse extends BaseResponse<PaymentStatusResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("payment_id")
        private String paymentId;

        @JsonProperty("payment_status")
        private Integer paymentStatus;

        @JsonProperty("status_description")
        private String statusDescription;

        private Long datetime;

        @JsonProperty("service_type")
        private String serviceType;

        private Object data;

        @JsonProperty("ofd_available")
        private Boolean ofdAvailable;

        @JsonProperty("service_name")
        private String serviceName;

        private String image;

        @JsonProperty("parameter_value")
        private String parameterValue;

        @JsonProperty("service_id")
        private Integer serviceId;

        @JsonProperty("ofd_barcode_data")
        private String ofdBarcodeData;
    }
}
