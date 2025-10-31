package com.example.api.evo.services.loyalty_card;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.loyalty_card.LoyaltyCardPartnersResponse;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsListResponse;
import com.example.utils.DateTimeUtil;

@Service
public class LoyaltyCardPartnersService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public LoyaltyCardPartnersResponse getLoyaltyCardPartnersList(Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("loyalty_card.partners")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(LoyaltyCardPartnersResponse.class);
    }

    public String getFirstLoyaltyCardPartnerId(Map<String, String> headers) {
        return this.getLoyaltyCardPartnersList(headers).getResult().getFirst().getId();
    }

    public LoyaltyCardsListResponse getLoyaltyCardsList(Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("loyalty_card.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(LoyaltyCardsListResponse.class);
    }

    public LoyaltyCardsListResponse.Result getLoyaltyCardByTitle(Map<String, String> headers, String title) {
        return this.getLoyaltyCardsList(headers).getResult().stream()
                .filter(card -> card.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Карта с названием '" + title + "' не найдена"));
    }

    public Optional<LoyaltyCardsListResponse.Result> getLoyaltyCardById(Map<String, String> headers, String id) {
        return this.getLoyaltyCardsList(headers).getResult().stream()
                .filter(card -> card.getId().equals(id))
                .findFirst();
    }
}
