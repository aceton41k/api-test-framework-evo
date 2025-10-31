package com.example.api.evo.models.loyalty_card;

import java.util.List;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class LoyaltyCardPartnersResponse extends BaseResponse<List<LoyaltyCardPartnersResponse.Result>> {
    @Getter
    public static class Result {
        private String id;
        private String title;
        private String logo;
        private String icon;
    }
}
