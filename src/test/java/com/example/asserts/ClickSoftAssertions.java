package com.example.asserts;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import com.example.api.evo.models.BaseErrorResponse;

public class ClickSoftAssertions extends SoftAssertions {
    public UrlAssert assertThatUrl(String url) {
        return proxy(UrlAssert.class, String.class, url);
    }

    public JsonValidate assertThat(Response response) {
        return proxy(JsonValidate.class, Response.class, response);
    }
    public BaseErrorResponseAssert assertThatError(BaseErrorResponse response) {
        return proxy(BaseErrorResponseAssert.class, BaseErrorResponse.class, response);
    }
}