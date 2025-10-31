package com.example.api.evo.services.card_applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.card_applications.CardAplBanksRequest;
import com.example.api.evo.models.card_applications.CardAplBanksResponse;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class CardAplService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public <T> T getCardAplDirectory(Map<String, String> headers, Class<T> responseType) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("card_apl.directory")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public CardAplBanksResponse getCardAplBanks(CardAplBanksRequest.Params params, Map<String, String> headers) {
        CardAplBanksRequest request = CardAplBanksRequest.builder()
                .method("card_apl.banks")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(CardAplBanksResponse.class);
    }
}
