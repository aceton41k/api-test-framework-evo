package com.example.api.evo.services.regular_payments;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.utils.DateTimeUtil;

@Service
public class RegularPaymentsListService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public <T>T getRegularPaymentsList(Class<T> responseType, Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("regular.payments.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public <T>T turnOnRegularPayments(Class<T> responseType, Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("regular.payments.on")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public <T>T turnOffRegularPayments(Class<T> responseType, Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("regular.payments.off")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }
}
