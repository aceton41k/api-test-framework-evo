package com.example.api.evo.services.payments;

import java.util.Map;

import com.example.api.evo.models.payment.*;
import com.example.evo.models.payment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import uz.click.evo_api.models.payment.*;
import com.example.utils.DateTimeUtil;

@Service
public class PaymentService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public PaymentMobileDataResponse getMobilePaymentData(
            PaymentMobileDataRequest.Params params, Map<String, String> headers) {
        var request = PaymentMobileDataRequest.builder()
                .method("payment.mobile.data")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(PaymentMobileDataResponse.class);
    }

    public PaymentDataResponse getPaymentData(PaymentDataRequest.Params params, Map<String, String> headers) {
        var request = PaymentDataRequest.builder()
                .method("payment.data")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(PaymentDataResponse.class);
    }

    public FormDataResponse getFormData(FormDataRequest.Params params, Map<String, String> headers) {
        var request = FormDataRequest.builder()
                .method("form.data")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(FormDataResponse.class);
    }

    public PaymentStatusResponse getPaymentStatus(PaymentStatusRequest.Params params, Map<String, String> headers) {
        var request = PaymentStatusRequest.builder()
                .method("payment.status")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(PaymentStatusResponse.class);
    }

    public FriendHelpResponse friendHelp(FriendHelpRequest.Params params, Map<String, String> headers) {
        var request = FriendHelpRequest.builder()
                .method("friend.help.request")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(FriendHelpResponse.class);
    }

    public GetLatestPaymentStatusResponse getLatestPaymentStatus(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("latest.payments.get")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetLatestPaymentStatusResponse.class);
    }
}
