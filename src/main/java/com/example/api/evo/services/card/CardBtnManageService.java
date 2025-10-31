package com.example.api.evo.services.card;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.card.CardBtnManageResponse;
import com.example.utils.DateTimeUtil;

@Service
public class CardBtnManageService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public CardBtnManageResponse getCardsManage() {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("card.btns.manage")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification().body(request).post().as(CardBtnManageResponse.class);
    }

    public <T>T getCardSuggestions(Class<T> responseType, Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("card.suggestions")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }
}
