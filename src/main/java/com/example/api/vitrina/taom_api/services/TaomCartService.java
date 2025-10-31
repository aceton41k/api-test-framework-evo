package com.example.api.vitrina.taom_api.services;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.vitrina.taom_api.TaomApiRequestSpecification;
import com.example.api.vitrina.taom_api.constants.TaomConstants;
import com.example.api.vitrina.taom_api.models.TaomCartDeliveryRequest;

@Service
public class TaomCartService {
    @Autowired
    TaomApiRequestSpecification reqSpec;

    public Response getCart(String serviceId, String token){
        return reqSpec.getRequestSpecification()
                .header("Authorization", token)
                .pathParam("merchantId", TaomConstants.MERCHANT_ID)
                .pathParam("serviceId", serviceId)
                .get("/{merchantId}/{serviceId}/cart");
    }

    public Response addItemToCart(String serviceId, String productId, String token){
        return reqSpec.getRequestSpecification()
                .header("Authorization", token)
                .pathParam("merchantId", TaomConstants.MERCHANT_ID)
                .pathParam("serviceId", serviceId)
                .pathParam("productId", productId)
                .post("/{merchantId}/{serviceId}/cart/add-item/{productId}");
    }

    public Response subItemFromCart(String serviceId, String productId, String token){
        return reqSpec.getRequestSpecification()
                .header("Authorization", token)
                .pathParam("merchantId", TaomConstants.MERCHANT_ID)
                .pathParam("serviceId", serviceId)
                .pathParam("productId", productId)
                .post("/{merchantId}/{serviceId}/cart/minus-item/{productId}");
    }

    public Response clearCart(String serviceId, String token){
        return reqSpec.getRequestSpecification()
                .header("Authorization", token)
                .pathParam("merchantId", TaomConstants.MERCHANT_ID)
                .pathParam("serviceId", serviceId)
                .post("/{merchantId}/{serviceId}/cart/clear-cart");
    }

    public Response updateCartDeliveryOrPickupInfo(TaomCartDeliveryRequest body, String serviceId, String orderId, String token){
        return reqSpec.getRequestSpecification()
                .header("Authorization", token)
                .pathParam("merchantId", TaomConstants.MERCHANT_ID)
                .pathParam("serviceId", serviceId)
                .pathParam("orderId", orderId)
                .body(body)
                .post("/{merchantId}/{serviceId}/cart/delivery-info/{orderId}");
    }


}
