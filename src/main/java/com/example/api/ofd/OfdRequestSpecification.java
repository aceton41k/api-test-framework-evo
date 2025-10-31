package com.example.api.ofd;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Service;
import com.example.BaseRequestSpecification;
import com.example.api.ofd.constants.OfdUrls;

import static io.restassured.RestAssured.given;
import static com.example.api.ofd.constants.OfdUrls.*;

@Service
public class OfdRequestSpecification extends BaseRequestSpecification {

    private String username;
    private String password;

    public void setAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private RequestSpecification buildReqSpec(String baseUri) {
        return given()
                .spec(createBaseSpec())
                .auth()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .baseUri(baseUri);
    }

    public RequestSpecification getSettingsReqSpec() {
        return buildReqSpec(OfdUrls.OFD_SETTINGS_URL);
    }

    public RequestSpecification getMerchantReqSpec() {
        return buildReqSpec(OFD_MERCHANT_URL);
    }

    public RequestSpecification getProducerMSReqSpec() {
        return buildReqSpec(PRODUCER_MS_URL);
    }

    public RequestSpecification getSupportMSReqSpec() {
        return buildReqSpec(SUPPORT_MS_URL);
    }
    public RequestSpecification getPriceListMsReqSpec() {
        return buildReqSpec(PRICE_LIST_MS_URL);
    }

    public RequestSpecification getReceiptReqSpec() {
        return buildReqSpec(OfdUrls.OFD_RECEIPT_URL);
    }
}
