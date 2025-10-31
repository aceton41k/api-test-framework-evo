package com.example.api.fin.services;

import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.fin.FinApiRequestSpecification;
import com.example.api.fin.constants.CardType;
import com.example.api.fin.models.BanksResponse;

import static com.example.api.fin.constants.FinApiConstants.COOKIE_WEB_SESSION;

@Service
public class FinApiAuthService {

    @Autowired
    FinApiRequestSpecification spec;

    @Step("Get Banks")
    public BanksResponse getBanks(String webSession, CardType cardType) {
        return spec.build()
                .cookie(COOKIE_WEB_SESSION, webSession)
                .queryParam("cardType", cardType)
                .get("/api/banks")
                .as(BanksResponse.class);
    }
}
