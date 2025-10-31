package com.example.api.travel;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;
import com.example.api.travel.constants.UnitTravelData;

import static io.restassured.RestAssured.given;

@Component
public class UnitTravelRequestSpecification extends BaseRequestSpecification {
    public RequestSpecification req() {
        return given().spec(createBaseSpec())
                .contentType(ContentType.JSON)
                .baseUri(UnitTravelData.BASE_URI);
    }
}