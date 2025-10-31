package com.example.api.evo.services.identification_group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.identification_group.GetIdentificationPaymentDataResponse;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class GetIdentificationPaymentDataService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public GetIdentificationPaymentDataResponse getIdentificationPaymentData(Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("identification.payment.data")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetIdentificationPaymentDataResponse.class);
    }
}
