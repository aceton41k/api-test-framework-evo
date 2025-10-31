package com.example.api.vitrina.vitrina_api.services;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.vitrina.vitrina_api.VitrinaApiRequestSpecification;
import com.example.api.vitrina.vitrina_api.constants.VitrinaConstants;


@Service
public class VitrinaCategoryService {
    @Autowired
    VitrinaApiRequestSpecification reqSpec;

    public Response getCategoryList(String token) {
        return reqSpec.getRequestSpecification()
                .auth().oauth2(token)
                .pathParams("serviceId", VitrinaConstants.SERVICE_ID_1)
                .get("/categories/{serviceId}");
    }
}
