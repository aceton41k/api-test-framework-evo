package com.example.api.evo.models.reports;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class HistoryMonitoringResponse extends BaseResponse<List<HistoryMonitoringResponse.Result>> {
    @Getter
    public static class Result {
        private Integer id;

        @JsonProperty("payment_id")
        private String paymentId;

        @JsonProperty("service_id")
        private String serviceId;

        private Integer amount;

        @JsonProperty("comission_amount")
        private Integer commissionAmount;

        private Integer state;

        @JsonProperty("payment_status")
        private Integer paymentStatus;

        @JsonProperty("payment_status_description")
        private String paymentStatusDescription;

        @JsonProperty("service_name")
        private String serviceName;

        private Long datetime;

        @JsonProperty("account_id")
        private String accountId;

        private String image;

        @JsonProperty("barcode_url")
        private String barcodeUrl;

        @JsonProperty("card_num")
        private String cardNum;

        private Boolean credit;
        private Boolean repeatable;

        @JsonProperty("favorite_permission")
        private Boolean favoritePermission;

        @JsonProperty("myhome_permission")
        private Boolean myhomePermission;

        private Boolean receipt;

        @JsonProperty("cancel_permission")
        private Boolean cancelPermission;

        @JsonProperty("service_type")
        private String serviceType;

        private String currency;

        @JsonProperty("ofd_barcode_data")
        private String ofdBarcodeData;

        @JsonProperty("ofd_available")
        private Boolean ofdAvailable;

        private Object barcode;
        private List<Object> data;

        @JsonProperty("_debug_date")
        private String debugDate;
    }
}
