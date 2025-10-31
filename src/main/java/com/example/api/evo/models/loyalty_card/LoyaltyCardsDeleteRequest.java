package com.example.api.evo.models.loyalty_card;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class LoyaltyCardsDeleteRequest extends BaseRequest<LoyaltyCardsDeleteRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String id;
    }
}
