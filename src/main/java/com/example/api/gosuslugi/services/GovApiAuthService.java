package com.example.api.gosuslugi.services;

import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.gosuslugi.GovApiRequestSpecification;
import com.example.api.gosuslugi.models.login.GovApiLoginRequest;
import com.example.api.gosuslugi.models.login.GovApiLoginResponse;

import static com.example.api.gosuslugi.constants.GovLoginConstants.CLICK_WEB_SESSION;

@Service
public class GovApiAuthService {
    @Autowired
    private GovApiRequestSpecification reqSpec;

    String basePath = "/auth/login";

    @Step("Login")
    public GovApiLoginResponse login(GovApiLoginRequest params, String webSession) {
        Cookie sessionCookie = new Cookie.Builder(CLICK_WEB_SESSION, webSession)
                .setDomain(".click.uz")
                .setPath("/")
                .build();
        return reqSpec.getRequestSpecification()
                .body(params)
                .cookie(sessionCookie)
                .basePath(basePath)
                .post()
                .as(GovApiLoginResponse.class);
    }
}
