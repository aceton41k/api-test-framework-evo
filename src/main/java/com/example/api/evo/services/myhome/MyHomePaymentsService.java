package com.example.api.evo.services.myhome;

import com.example.api.evo.models.myhome.*;
import com.example.evo.models.myhome.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.ResponseWithOkResult;
import uz.click.evo_api.models.myhome.*;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class MyHomePaymentsService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public ResponseWithOkResult addPaymentToMyHome(
            MyHomePaymentsSaveRequest.Params params, Map<String, String> headers) {
        var request = MyHomePaymentsSaveRequest.builder()
                .method("myhome.payments.save")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();

        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public MyHomePaymentsListResponse getMyHomePaymentList(
            MyHomePaymentsListRequest.Params params, Map<String, String> headers) {
        var request = MyHomePaymentsListRequest.builder()
                .method("myhome.payments.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(MyHomePaymentsListResponse.class);
    }

    public ResponseWithOkResult deletePaymentFromHome(
            MyHomePaymentsDeleteRequest.Params params, Map<String, String> headers) {
        var request = MyHomePaymentsDeleteRequest.builder()
                .method("myhome.payments.delete")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public MyHomePaymentDataResponse getMyHomePaymentData(
            MyHomePaymentDataRequest.Params params, Map<String, String> headers) {
        var request = MyHomePaymentDataRequest.builder()
                .method("myhome.payments.data")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(MyHomePaymentDataResponse.class);
    }

    public MyHomePaymentInfoResponse getMyHomePaymentInfo(
            MyHomePaymentDataRequest.Params params, Map<String, String> headers) {
        var request = MyHomePaymentDataRequest.builder()
                .method("myhome.payments.info")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(MyHomePaymentInfoResponse.class);
    }

    public ResponseWithOkResult addToMyHomeFromPayment(
            MyHomeFromPaymentRequest.Params params, Map<String, String> headers) {
        var request = MyHomeFromPaymentRequest.builder()
                .method("myhome.from.payment")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult addToMyHomeFromFavorite(
            MyHomeFromFavoriteRequest.Params params, Map<String, String> headers) {
        var request = MyHomeFromFavoriteRequest.builder()
                .method("myhome.from.favorite")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }
}
