package com.example.api.integration_client_ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.integration_client_ms.IntegrationApiMSRequestSpecification;

import java.util.Map;

@Service
public class GetTokenService {
    @Autowired
    IntegrationApiMSRequestSpecification integrationApiMSRequestSpecification;

    public <T> T getToken(Map<String, String> request, Class<T> response) {
        return integrationApiMSRequestSpecification.buildReqSpec().basePath("/v1/cards/token").queryParams(request)
                                                   .get().as(response);
    }
}
