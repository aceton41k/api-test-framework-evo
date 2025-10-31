package com.example.api.fin;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;

import static io.restassured.RestAssured.given;
import static com.example.api.fin.constants.FinApiConstants.FIN_API_BASE_URI;

@Component
public class FinApiRequestSpecification extends BaseRequestSpecification {

    public RequestSpecification build() {
        return given()
                .spec(createBaseSpec())
                .baseUri(FIN_API_BASE_URI)
                .contentType(ContentType.JSON);
    }
}
