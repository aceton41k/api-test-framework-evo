package com.example.api.vitrina.vitrina_api.services;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.vitrina.vitrina_api.VitrinaApiRequestSpecification;
import com.example.api.vitrina.vitrina_api.constants.VitrinaConstants;
import com.example.api.vitrina.vitrina_api.models.auth.VitrinaAuthRequest;
import com.example.api.vitrina.vitrina_api.models.auth.VitrinaAuthResponse;

import java.util.Map;

@Service
public class VitrinaAuthService {
    @Autowired
    VitrinaApiRequestSpecification reqSpec;

    public Response auth(VitrinaAuthRequest req, Map<String, String> headers) {
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(req)
                .post("click-business/auth");
    }

    public VitrinaAuthResponse authDevUser() {
        Map<String, String> headers = Map.of("Authorization", "dev_user");
        VitrinaAuthRequest req = VitrinaAuthRequest.builder()
                .merchantId(VitrinaConstants.MERCHANT_ID_DEV_USER)
                .build();

        return auth(req, headers).as(VitrinaAuthResponse.class);
    }
}
