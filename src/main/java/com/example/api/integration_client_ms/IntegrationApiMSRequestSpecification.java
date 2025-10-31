package com.example.api.integration_client_ms;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Service;
import com.example.BaseRequestSpecification;

import static io.restassured.RestAssured.given;
import static com.example.api.integration_client_ms.constants.IntegrationsApiMSUrls.INTEGRATION_API_CLIENT_MS;

@Service
public class IntegrationApiMSRequestSpecification extends BaseRequestSpecification {


    private String username;
    private String password;

    public void setAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RequestSpecification buildReqSpec() {
        return given().spec(createBaseSpec()).contentType(ContentType.JSON).baseUri(INTEGRATION_API_CLIENT_MS).auth()
                      .basic(username, password);
    }

}
