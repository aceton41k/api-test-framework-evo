package com.example.api.evo.services.loyalty_card;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsAddRequest;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsDeleteRequest;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsEditRequest;
import com.example.utils.DateTimeUtil;

@Service
public class LoyaltyCardsService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public ResponseWithOkResult addLoyaltyCard(LoyaltyCardsAddRequest.Params params, Map<String, String> headers) {
        LoyaltyCardsAddRequest request = LoyaltyCardsAddRequest.builder()
                .method("loyalty_card.add")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult editLoyaltyCard(LoyaltyCardsEditRequest.Params params, Map<String, String> headers) {
        LoyaltyCardsEditRequest request = LoyaltyCardsEditRequest.builder()
                .method("loyalty_card.edit")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult deleteLoyaltyCard(
            LoyaltyCardsDeleteRequest.Params params, Map<String, String> headers) {
        LoyaltyCardsDeleteRequest request = LoyaltyCardsDeleteRequest.builder()
                .method("loyalty_card.remove")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }
}
