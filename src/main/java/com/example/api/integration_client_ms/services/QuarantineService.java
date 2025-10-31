package com.example.api.integration_client_ms.services;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.integration_client_ms.IntegrationApiMSRequestSpecification;

import java.util.Map;

@Service
public class QuarantineService {
    @Autowired
    IntegrationApiMSRequestSpecification integrationApiMSRequestSpecification;

    public Response blockCard(Map<String, String> request) {
        return integrationApiMSRequestSpecification.buildReqSpec().basePath("/v1/quarantine/block").body(request).put();
    }

    public Response unblockCard(Map<String, String> request) {
        return integrationApiMSRequestSpecification.buildReqSpec().basePath("/v1/quarantine/unblock").body(request)
                                                   .put();
    }
}
