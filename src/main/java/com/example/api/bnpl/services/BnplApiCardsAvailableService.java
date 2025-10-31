package com.example.api.bnpl.services;

import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.bnpl.BnplApiRequestSpecification;
import com.example.api.bnpl.models.CardsAvailableResponse;

@Service
public class BnplApiCardsAvailableService {

    @Autowired
    BnplApiRequestSpecification reqSpec;

    @Step("Get Cards")
    public CardsAvailableResponse getCardsAvailableList(int clientId, String bnplPartner) {
        return reqSpec.build()
                .queryParam("clientId", clientId)
                .queryParam("bnplPartner", bnplPartner)
                .get("/api/internal/v1/accounts/available")
                .as(CardsAvailableResponse.class);
    }
}
