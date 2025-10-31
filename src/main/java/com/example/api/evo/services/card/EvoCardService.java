package com.example.api.evo.services.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.card.CardChangeNameRequest;
import com.example.api.evo.models.card.GetBalanceRequest;
import com.example.api.evo.models.card.GetCardNumberRequest;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class EvoCardService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public <T> T getCards(Class<T> responseType, Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("get.cards")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public <T> T getCardNumber(GetCardNumberRequest.Params params, Class<T> responseType, Map<String, String> headers) {
        GetCardNumberRequest request = GetCardNumberRequest.builder()
                .method("card.get.number")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public <T> T getBalance(GetBalanceRequest.Params params, Class<T> responseType, Map<String, String> headers) {
        GetBalanceRequest request = GetBalanceRequest.builder()
                .method("get.balance")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public <T> T changeCardName(CardChangeNameRequest.Params params, Class<T> responseType, Map<String, String> headers) {
        CardChangeNameRequest request = CardChangeNameRequest.builder()
                .method("card.change.name")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public <T> T getHumoPayDetails(GetBalanceRequest.Params params, Class<T> responseType, Map<String, String> headers) {
        GetBalanceRequest request = GetBalanceRequest.builder()
                .method("humopay.details")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }
}
