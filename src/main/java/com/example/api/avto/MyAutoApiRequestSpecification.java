package com.example.api.avto;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;
import com.example.api.avto.constants.TestConfig;

import static io.restassured.RestAssured.given;

@Component
public class MyAutoApiRequestSpecification extends BaseRequestSpecification {

    public RequestSpecification getRequestSpecification() {
        return given().spec(createBaseSpec())
                .contentType(ContentType.JSON)
                .baseUri(TestConfig.BASE_URI);
    }
}
