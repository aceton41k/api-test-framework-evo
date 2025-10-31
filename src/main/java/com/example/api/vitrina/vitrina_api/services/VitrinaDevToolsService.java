package com.example.api.vitrina.vitrina_api.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.vitrina.vitrina_api.VitrinaApiRequestSpecification;
import com.example.api.vitrina.vitrina_api.constants.VitrinaConstants;

@Slf4j
@Service
public class VitrinaDevToolsService {
    @Autowired
    VitrinaApiRequestSpecification reqSpec;

    public void resetVitrina(String vitrinaToken) {
        if (vitrinaToken == null || vitrinaToken.isBlank()) {
            log.info("vitrinaToken is null or blank — skipping platform reset");
            return;
        }

        try {
            Response resetResponse = reqSpec.getRequestSpecification()
                    .header("Authorization", "Bearer " + vitrinaToken)
                    .contentType(ContentType.JSON)
                    .body("""
                            {
                              "platform_app_id": null,
                              "platform_is_moderated": false,
                              "platform_sent": false
                            }
                            """)
                    .when()
                    .pathParams("serviceId", VitrinaConstants.SERVICE_SERVICE_ID_DEV_USER, "merchantId", VitrinaConstants.MERCHANT_ID_DEV_USER)
                    .patch("custom-service-registration/reset-platform/{serviceId}/{merchantId}")
                    .then()
                    .extract().response();

            log.info("Platform fields reset. Status: {}, Service ID: " + VitrinaConstants.SERVICE_ID_DEV_USER, resetResponse.getStatusCode());

        } catch (Exception e) {
            log.warn("Failed to reset platform fields: {}", e.getMessage());
        }
    }
}
