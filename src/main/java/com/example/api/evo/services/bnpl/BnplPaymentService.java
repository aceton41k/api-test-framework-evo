package com.example.api.evo.services.bnpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.bnpl.BnplPaymentRequest;
import com.example.api.evo.models.bnpl.BnplPaymentResponse;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class BnplPaymentService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public BnplPaymentResponse createPayment(BnplPaymentRequest.Params params, Map<String, String> headers) {
        BnplPaymentRequest request = BnplPaymentRequest.builder()
                .method("bnpl.payment")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();

        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(BnplPaymentResponse.class);
    }
}

