package com.example.api.evo.models.bnpl;

import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class BnplAllowedCardsResponse extends BaseResponse<BnplAllowedCardsResponse.Result> {
    @Getter
    public static class Result {
        private List<String> cards;
    }
}
