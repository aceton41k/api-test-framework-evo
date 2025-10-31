package com.example.api.vitrina.taom_api.services;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.vitrina.taom_api.TaomApiRequestSpecification;
import com.example.api.vitrina.taom_api.constants.TaomConstants;

@Service
public class TaomOrderService {
    @Autowired
    TaomApiRequestSpecification reqSpec;

    public Response getOrders(String serviceId, String token){
        return reqSpec.getRequestSpecification()
                .header("Authorization", token)
                .pathParam("merchantId", TaomConstants.MERCHANT_ID)
                .pathParam("serviceId", serviceId)
                .get("/{merchantId}/{serviceId}/orders/list");
    }
}
