package com.example.api.merchant;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;

import static io.restassured.RestAssured.given;
import static com.example.api.merchant.constants.MerchantUrls.MERCHANT_PROD_URL;

@Component
public class MerchantApiRequestSpecification extends BaseRequestSpecification {
    public RequestSpecification getRequestSpecification() {
        return given().spec(createBaseSpec())
                .contentType(ContentType.JSON)
                .baseUri(MERCHANT_PROD_URL)
                .accept(ContentType.JSON);
    }
}
