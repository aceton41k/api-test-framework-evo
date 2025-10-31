package com.example.api.ussd;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;
import com.example.api.ussd.constants.TestConfig;


import static io.restassured.RestAssured.given;

@Component
public class UssdRequestSpecification extends BaseRequestSpecification {

    public RequestSpecification getRequestSpecification() {
        return given().spec(createBaseSpec())
                .contentType(ContentType.JSON)
                .baseUri(TestConfig.BASE_URI);
    }
}

