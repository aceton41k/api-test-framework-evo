package com.example.api.evo.models.regular_payments;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class RegularPaymentsListResponse extends BaseResponse<List<RegularPaymentsListResponse.Result>> {
    @Getter
    public static class Result {
        private Service service;

        @JsonProperty("payment_id")
        private String paymentId;

        private String parameter;
        private Integer amount;
        private Long datetime;

        @Getter
        public static class Service {
            private int id;
            private String name;
            private String image;

            @JsonProperty("category_id")
            private Integer categoryId;

            private Boolean maintenance;

            @JsonProperty("min_limit")
            private Integer minLimit;

            @JsonProperty("max_limit")
            private Integer maxLimit;

            @JsonProperty("card_types")
            private List<String> cardTypes;
        }
    }
}
