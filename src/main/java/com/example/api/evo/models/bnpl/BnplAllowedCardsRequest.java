package com.example.api.evo.models.bnpl;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class BnplAllowedCardsRequest extends BaseRequest<BnplAllowedCardsRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String partner;
    }
}
