package com.example.api.egov;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Service;
import com.example.BaseRequestSpecification;

import static io.restassured.RestAssured.given;
import static com.example.api.egov.constants.EgovUrls.E_GOV_URL;

@Service
public class EGovRequestSpecification extends BaseRequestSpecification {

    private String username;
    private String password;

    public void setAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RequestSpecification buildReqSpec() {
        return given()
                .spec(createBaseSpec())
                .auth()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .baseUri(E_GOV_URL);
    }

}
