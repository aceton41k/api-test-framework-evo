package com.example.api.evo.models.reports;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class HistoryClickResponse extends BaseResponse<List<HistoryClickResponse.Result>> {
    @Getter
    public static class Result {
        private Long id;

        @JsonProperty("payment_id")
        private Long paymentId;

        @JsonProperty("service_id")
        private Integer serviceId;

        private Long amount;
        private String currency;
        private String parameter;
        private Integer state;

        @JsonProperty("service_name")
        private String serviceName;

        private Long datetime;
        private String image;
        private Boolean repeatable;
        private Boolean credit;
        private Integer hold;
    }
}
