package com.example.api.vitrina.taom_api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;
import com.example.api.vitrina.taom_api.constants.TaomUrls;

import static io.restassured.RestAssured.given;

@Component
public class TaomApiRequestSpecification extends BaseRequestSpecification {
    public RequestSpecification getRequestSpecification() {
        return given().spec(createBaseSpec()).contentType(ContentType.JSON).baseUri(TaomUrls.TAOM_TEST_URL);
    }
}
