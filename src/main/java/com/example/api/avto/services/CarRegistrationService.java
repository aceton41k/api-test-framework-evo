package com.example.api.avto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.avto.MyAutoApiRequestSpecification;
import com.example.api.avto.models.car_registration.CarRegInvoicePostRequest;
import com.example.api.avto.models.car_registration.CarRegInvoicePostResponse;
import com.example.api.avto.models.car_registration.CarRegInvoiceStatusPostRequest;

@Service
public class CarRegistrationService {
    @Autowired
    private MyAutoApiRequestSpecification reqSpec;

    public CarRegInvoicePostResponse addCarRegInvoice(String token, CarRegInvoicePostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/car-registration/parse-sms")
                .as(CarRegInvoicePostResponse.class);
    }

    public CarRegInvoicePostResponse getInvoiceStatus(String token, CarRegInvoiceStatusPostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/car-registration/invoices/status")
                .as(CarRegInvoicePostResponse.class);
    }
}
