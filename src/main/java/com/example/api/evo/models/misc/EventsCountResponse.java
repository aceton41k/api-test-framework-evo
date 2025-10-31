package com.example.api.evo.models.misc;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class EventsCountResponse extends BaseResponse<EventsCountResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("overall_count")
        private Integer overallCount;

        @JsonProperty("unread_count")
        private Integer unreadCount;

        @JsonProperty("requests_count")
        private Integer requestsCount;

        @JsonProperty("receipt_count")
        private Integer receiptCount;

        @JsonProperty("invoice_count")
        private Integer invoiceCount;

        @JsonProperty("last_news_id")
        private String lastNewsId;

        @JsonProperty("unread_news_count")
        private String unreadNewsCount;

        @JsonProperty("menu_more_count")
        private Integer menuMoreCount;
    }
}
