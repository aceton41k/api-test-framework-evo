package com.example.api.vitrina.vitrina_api.services;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.api.vitrina.vitrina_api.constants.VitrinaConstants;

import static io.restassured.RestAssured.given;

@Slf4j
@Service
public class PlatformService {
    public Response getMiniApp(String platformAppId) {
        return given()
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer " + VitrinaConstants.platform_token)
                .when()
                .get("url" + platformAppId)
                .then()
                .extract().response();
    }

    public void deleteMiniApp(String platformAppId) {
        if (platformAppId == null) {
            log.info("platformAppId is null, DELETE skipped");
            return;
        }

        try {
            Response response = given()
                    .relaxedHTTPSValidation()
                    .header("Authorization", "Bearer " + VitrinaConstants.platform_token)
                    .when()
                    .delete("url" + platformAppId)
                    .then()
                    .extract().response();

            log.info("Mini-app deleted. Status: {}, ID: {}", response.getStatusCode(), platformAppId);
        } catch (Exception e) {
            log.warn("Failed to delete mini-app {}: {}", platformAppId, e.getMessage(), e);
        }
    }

}
