package com.example.api.evo.models.invoice;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class InvoiceListResponse extends BaseResponse<List<InvoiceListResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("invoice_id")
        private Long invoiceId;

        @JsonProperty("service_id")
        private Integer serviceId;

        private Integer amount;
        private Integer commission;

        @JsonProperty("service_name")
        private String serviceName;

        private Long datetime;
        private List<Result.DataItem> data;

        @JsonProperty("friend_request")
        private String friendRequest;

        private Integer status;

        @JsonProperty("status_description")
        private String statusDescription;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        private String image;

        @Getter
        public static class DataItem {
            private String key;
            private String value;
            private Boolean main;
        }
    }
}
