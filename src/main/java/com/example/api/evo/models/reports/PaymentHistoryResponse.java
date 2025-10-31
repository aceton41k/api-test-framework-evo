package com.example.api.evo.models.reports;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class PaymentHistoryResponse extends BaseResponse<PaymentHistoryResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("payment_id")
        private Long paymentId;

        @JsonProperty("service_id")
        private Integer serviceId;

        private Integer amount;

        @JsonProperty("comission_amount")
        private Integer commissionAmount;

        private Integer state;

        @JsonProperty("payment_status")
        private Integer paymentStatus;

        @JsonProperty("payment_status_description")
        private String paymentStatusDescription;

        @JsonProperty("autopay_schedule_available")
        private String autopayScheduleAvailable;

        @JsonProperty("service_name")
        private String serviceName;

        private Long datetime;

        private String currency;

        @JsonProperty("account_id")
        private String accountId;

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

        @JsonProperty("ofd_available")
        private Boolean ofdAvailable;

        @JsonProperty("ofd_barcode_data")
        private String ofdBarcodeData;

        @JsonProperty("cashback_data")
        private CashbackData cashbackData;

        private Barcode barcode;

        private List<Data> data;

        @Getter
        public static class CashbackData {
            private Integer status;
        }

        @Getter
        public static class Barcode {
            private String image;
            private String header;
            private String footer;
        }

        @Getter
        public static class Data {
            private String key;
            private String value;
            private Boolean main;
        }
    }
}
