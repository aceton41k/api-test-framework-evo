package com.example.api.evo.models.card;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class GetBalanceRequest extends BaseRequest<GetBalanceRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("account_id")
        private List<Long> accountId;
    }
}
