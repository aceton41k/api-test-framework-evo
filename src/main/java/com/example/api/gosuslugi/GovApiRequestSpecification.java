package com.example.api.gosuslugi;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;

import static io.restassured.RestAssured.given;
import static com.example.api.gosuslugi.constants.GovUrls.GOV_PROD_URL;

@Component
public class GovApiRequestSpecification extends BaseRequestSpecification {
    private String baseUri = GOV_PROD_URL;

    public RequestSpecification getRequestSpecification() {
        return given().spec(createBaseSpec()).contentType(ContentType.JSON).baseUri(baseUri);
    }
}
