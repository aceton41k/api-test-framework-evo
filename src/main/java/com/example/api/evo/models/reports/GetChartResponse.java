package com.example.api.evo.models.reports;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetChartResponse extends BaseResponse<List<GetChartResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("category_id")
        private Long categoryId;

        @JsonProperty("service_name")
        private String serviceName;

        private Long amount;
        private Long percent;

        @JsonProperty("total_amount")
        private Long totalAmount;

        private String image;
    }
}
