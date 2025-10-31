package com.example.api.autopayments;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Service;
import com.example.BaseRequestSpecification;

import static io.restassured.RestAssured.given;
import static com.example.api.autopayments.constants.AutoPaymentUrls.AUTOPAYMENT_MS_URL;

@Service
public class AutoPaymentRequestSpecification extends BaseRequestSpecification {
    public RequestSpecification getRequestSpecification() {
        return given().spec(createBaseSpec()).baseUri(AUTOPAYMENT_MS_URL).contentType(ContentType.JSON);
    }

}
