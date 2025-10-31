package com.example.api.bnpl.services;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.bnpl.BnplApiRequestSpecification;
import com.example.api.bnpl.models.CheckoutResponse;
import com.example.api.bnpl.models.CheckoutWithPartnerRequest;

@Service
public class BnplApiCheckoutService {

    @Autowired
    BnplApiRequestSpecification reqSpec;

    public CheckoutResponse getCheckout(CheckoutWithPartnerRequest request) {
        return reqSpec.build()
                .body(request)
                .post("/api/internal/v2/checkout")
                .as(CheckoutResponse.class);
    }

    public Response getValidationError(CheckoutWithPartnerRequest request) {
        return reqSpec.build()
                .body(request)
                .post("/api/internal/v2/checkout");
    }
}
