package com.example.api.evo.models.reports;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class HistoryLatestResponse extends BaseResponse<List<HistoryLatestResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("payment_id")
        private Long paymentId;

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
        private Long accountId;

        private String image;

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

        private String id;
        private Barcode barcode;
        private List<Object> data;

        @JsonProperty("ofd_available")
        private Boolean ofdAvailable;

        @JsonProperty("ofd_barcode_data")
        private String ofdBarcodeData;
    }

    @Getter
    public static class Barcode {
        private String image;
        private String header;
        private String footer;
    }
}
