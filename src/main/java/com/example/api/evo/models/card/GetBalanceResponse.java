package com.example.api.evo.models.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class GetBalanceResponse extends BaseResponse<List<GetBalanceResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("account_id")
        private Long accountId;

        private Long balance;
    }
}
