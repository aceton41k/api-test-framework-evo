package com.example.api.bnpl;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;

import static io.restassured.RestAssured.given;
import static com.example.api.bnpl.constants.BnplUrls.BNPL_DEV_URL;

@Component
public class BnplApiRequestSpecification extends BaseRequestSpecification {

    public RequestSpecification build() {
        return given()
                .spec(createBaseSpec())
                .baseUri(BNPL_DEV_URL)
                .contentType(ContentType.JSON);
    }
}
