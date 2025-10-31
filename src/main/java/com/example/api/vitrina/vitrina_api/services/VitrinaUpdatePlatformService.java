package com.example.api.vitrina.vitrina_api.services;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.vitrina.vitrina_api.VitrinaApiRequestSpecification;
import com.example.api.vitrina.vitrina_api.constants.VitrinaConstants;

@Slf4j
@Service
public class VitrinaUpdatePlatformService {
    @Autowired
    VitrinaApiRequestSpecification reqSpec;

    public Response updatePlatform(String vitrina_token) {
        Response response = reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + vitrina_token)
                .pathParam("serviceId", VitrinaConstants.SERVICE_ID_DEV_USER)
                .post("services/{serviceId}/update-platform");

        log.info("Platform app created with ID: {}", response.jsonPath().getString("platform_app_id"));
        return response;
    }
}
