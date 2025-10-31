package com.example.api.evo.models.misc;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class EventListResponse extends BaseResponse<List<EventListResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("invoice_id")
        private Long invoiceId;

        @JsonProperty("service_id")
        private Long serviceId;

        private Long amount;
        private Long commission;

        @JsonProperty("service_name")
        private String serviceName;

        private Long datetime;
        private List<DataItem> data;

        @JsonProperty("friend_request")
        private String friendRequest;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        private String image;

        @JsonProperty("event_type")
        private String eventType;

        @JsonProperty("chat_id")
        private Long chatId;
    }

    @Getter
    public static class DataItem {
        private String key;
        private String value;
        private Boolean main;
    }
}
