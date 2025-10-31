package com.example.api.bnpl.services;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.bnpl.BnplApiRequestSpecification;

public class BnplApiActiveProvidersService {
    @Autowired
    BnplApiRequestSpecification reqSpec;

    public Response getActiveProviders() {
        return reqSpec.build()
                .get("/api/arm/v1/providers");
    }
}
