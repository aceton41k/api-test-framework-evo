package com.example.api.autopayments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.autopayments.AutoPaymentRequestSpecification;
import com.example.api.autopayments.autopayment_ms.CreateAutoPayRequest;

import java.util.Map;

@Service
public class AutoPaymentMSService {

    @Autowired
    AutoPaymentRequestSpecification autoPaymentRequestSpecification;

    public <T> T createAutoPayment(CreateAutoPayRequest request, Map<String, String> headers, Class<T> responseType) {
        return autoPaymentRequestSpecification.getRequestSpecification().basePath("v2/autopay").headers(headers)
                                              .body(request).post().as(responseType);
    }

    public <T> T getAutoPayment(Map<String, Object> params, Map<String, String> headers, Class<T> responseType) {
        return autoPaymentRequestSpecification.getRequestSpecification().basePath("v2/autopay").headers(headers)
                                              .queryParams(params).get().as(responseType);
    }
}
