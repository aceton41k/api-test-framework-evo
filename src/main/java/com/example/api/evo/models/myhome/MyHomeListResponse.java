package com.example.api.evo.models.myhome;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.time.ZonedDateTime;
import java.util.List;

public class MyHomeListResponse extends BaseResponse<List<MyHomeListResponse.Result>> {
    @Getter
    public static class Result {
        private Long id;
        @JsonProperty("user_id")
        private Integer userId;
        private String title;
        private String address;
        @JsonProperty("is_deleted")
        private Boolean isDeleted;
        private Integer order;
        @JsonProperty("updated_at")
        private ZonedDateTime updatedAt;
        @JsonProperty("created_at")
        private ZonedDateTime createdAt;
        private String name;
        @JsonProperty("payment_date")
        private ZonedDateTime paymentDate;
        @JsonProperty("payment_amount")
        private Double paymentAmount;
    }
}
