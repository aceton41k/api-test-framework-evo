package com.example.api.evo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.identification.IdentificationRequest;
import com.example.api.evo.models.identification.IdentificationResponse;
import com.example.api.evo.models.identification.IdentificationVerifyRequest;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class IdentificationService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public IdentificationResponse verify(IdentificationVerifyRequest.Params params, Map<String, String> headers) {
        var request = IdentificationVerifyRequest.builder()
                .method("identification.verify")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();

        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(IdentificationResponse.class);
    }

    public IdentificationResponse request(IdentificationRequest.Params params, Map<String, String> headers) {
        var request = IdentificationRequest.builder()
                .method("identification.request")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();

        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(IdentificationResponse.class);
    }
}
