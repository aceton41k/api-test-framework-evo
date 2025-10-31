package com.example.api.evo.models.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

public class GetCardNumberResponse extends BaseResponse<GetCardNumberResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("card_number")
        private String cardNumber;
    }
}
