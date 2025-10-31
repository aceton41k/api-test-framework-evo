package com.example.api.travel;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;
import com.example.BaseRequestSpecification;
import com.example.api.travel.constants.TravelMyAgentData;

import static io.restassured.RestAssured.given;

@Component
public class TravelMyAgentRequestSpecification extends BaseRequestSpecification {
    public RequestSpecification req() {
        return given().spec(createBaseSpec())
                .contentType(ContentType.JSON)
                .baseUri(TravelMyAgentData.MY_AGENT_BASE_URI)
                .header("x-api-key", TravelMyAgentData.apiKey());
    }
}
