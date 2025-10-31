package com.example.api.evo.services.bnpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.bnpl.BnplAllowedCardsRequest;
import com.example.api.evo.models.bnpl.BnplAllowedCardsResponse;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class BnplAllowedCardsService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public BnplAllowedCardsResponse getBnplAllowedCards(BnplAllowedCardsRequest.Params params, Map<String, String> headers) {
        BnplAllowedCardsRequest request = BnplAllowedCardsRequest.builder()
                .method("bnpl.card.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(BnplAllowedCardsResponse.class);
    }
}

