package com.example.api.vitrina.vitrina_api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;

import static io.restassured.RestAssured.given;
import static com.example.api.vitrina.vitrina_api.constants.VitrinaUrls.VITRINA_TEST_URL;

@Component
public class VitrinaApiRequestSpecification extends BaseRequestSpecification {
    public RequestSpecification getRequestSpecification() {
        return given().spec(createBaseSpec()).contentType(ContentType.JSON).baseUri(VITRINA_TEST_URL);
    }
}
