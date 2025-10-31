package com.example.api.evo.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class GetLatestPaymentStatusResponse extends BaseResponse<List<GetLatestPaymentStatusResponse.Result>> {
    @Getter
    public static class Result {
        private Service service;
        @JsonProperty("payment_id")
        private String paymentId;
        private String parameter;
        private Integer amount;
        private Long datetime;
    }

    @Getter
    public static class Service {
        private Integer id;
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
