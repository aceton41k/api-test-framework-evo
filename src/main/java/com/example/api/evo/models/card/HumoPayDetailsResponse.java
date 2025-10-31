package com.example.api.evo.models.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

public class HumoPayDetailsResponse extends BaseResponse<HumoPayDetailsResponse.Result> {
    @Getter
    public static class Result {
        private String pan;
        @JsonProperty("client_id")
        private String clientId;
    }
}
