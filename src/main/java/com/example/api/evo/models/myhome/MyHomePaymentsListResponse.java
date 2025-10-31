package com.example.api.evo.models.myhome;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class MyHomePaymentsListResponse extends BaseResponse<List<MyHomePaymentsListResponse.Result>> {
    @Getter
    public static class Result {
        private Long id;
        private String name;

        @JsonProperty("home_id")
        private Integer homeId;
        @JsonProperty("click_service_id")
        private Integer clickServiceId;
        @JsonProperty("service_id")
        private Integer serviceId;

        private String image;
        private String datetime;

        @JsonProperty("payment_status")
        private String paymentStatus;

        @JsonProperty("payment_status_note")
        private String paymentStatusNote;

        private Boolean maintenance;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        private DataObject data;

        @Getter
        public static class DataObject {
            private String parameter;
            private Integer amount;
            private String balance;
        }
    }
}
