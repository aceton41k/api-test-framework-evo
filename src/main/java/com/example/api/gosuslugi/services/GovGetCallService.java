package com.example.api.gosuslugi.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.gosuslugi.GovApiRequestSpecification;

import java.util.Map;

@Service
public class GovGetCallService {

    @Autowired
    private GovApiRequestSpecification reqSpec;

    private final String basePath = "/callService";

    @Step("Вызов метода /callService")
    public <REQ, RES> RES callService(
            REQ request,
            Class<RES> responseType,
            Map<String, String> headers
    ) {
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .basePath(basePath)
                .body(request)
                .post()
                .as(responseType);
    }

    @Step("Вызов /callService (для проверки JSON-схемы)")
    public <REQ> Response callRawService(
            REQ request,
            Map<String, String> headers
    ) {
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .basePath(basePath)
                .body(request)
                .post();
    }
}