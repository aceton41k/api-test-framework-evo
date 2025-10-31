package com.example.api.evo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import lombok.Setter;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;

import static io.restassured.RestAssured.given;

@Setter
@Component
public class EvoApiRequestSpecification extends BaseRequestSpecification {

    public RequestSpecification getRequestSpecification() {
        RestAssured.defaultParser = Parser.JSON;
        return given()
                .spec(createBaseSpec())
                .contentType(ContentType.JSON)
                .headers("Accept-version", "2.3.0");
    }
}
