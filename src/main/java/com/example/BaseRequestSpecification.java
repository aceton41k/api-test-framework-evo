package com.example;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;

public class BaseRequestSpecification {
    public RequestSpecification createBaseSpec() {
        return given()
                .filters(
                        new AllureRestAssured(), new ResponseLoggingFilter(ALL), new RequestLoggingFilter(ALL)
                )
                .relaxedHTTPSValidation();
    }
}
