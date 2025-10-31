package com.example.asserts;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonValidate extends AbstractAssert<JsonValidate, Response> {

    public JsonValidate(Response actual) {
        super(actual, JsonValidate.class);
    }

    public void validateJsonSchema(String jsonSchemaPath) {
        actual.then().body(matchesJsonSchemaInClasspath(jsonSchemaPath));
    }
}
