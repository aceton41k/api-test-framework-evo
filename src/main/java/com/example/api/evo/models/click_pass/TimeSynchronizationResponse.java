package com.example.api.evo.models.click_pass;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
public class TimeSynchronizationResponse extends BaseResponse<TimeSynchronizationResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("client_time")
        private Long clientTime;

        @JsonProperty("receive_time")
        private Long receiveTime;

        @JsonProperty("transmit_time")
        private Long transmitTime;
    }
}
