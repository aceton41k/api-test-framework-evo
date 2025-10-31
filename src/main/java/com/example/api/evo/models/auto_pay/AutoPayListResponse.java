package com.example.api.evo.models.auto_pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class AutoPayListResponse extends BaseResponse<List<AutoPayListResponse.Autopay>> {
    @Getter
    public static class Autopay {
        private Long id;

        @JsonProperty("autopay_type")
        private Integer autopayType;

        @JsonProperty("service_id")
        private Long serviceId;

        private Long datetime;
        private Float amount;
        private String value;

        @JsonProperty("account_id")
        private Long accountId;

        private String name;
        private Integer status;
        private String image;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        @JsonProperty("status_text")
        private String statusText;

        private String description;
    }
}
