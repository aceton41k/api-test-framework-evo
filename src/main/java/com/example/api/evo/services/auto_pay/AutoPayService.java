package com.example.api.evo.services.auto_pay;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.auto_pay.AutoPayDataRequest;
import com.example.api.evo.models.auto_pay.AutoPayDeleteRequest;
import com.example.api.evo.models.auto_pay.AutoPayListResponse;
import com.example.api.evo.models.auto_pay.AutoPaySaveRequest;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class AutoPayService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public AutoPayListResponse autoPayList(Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("autopay.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();

        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(AutoPayListResponse.class);
    }

    public Response autoPayData(AutoPayDataRequest.Params params, Map<String, String> headers) {
        AutoPayDataRequest request = AutoPayDataRequest.builder()
                .method("autopay.data")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();

        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post();
    }

    public ResponseWithOkResult autoPaySave(AutoPaySaveRequest.Params params, Map<String, String> headers) {

        AutoPaySaveRequest request = AutoPaySaveRequest.builder()
                .method("autopay.save")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();

        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult autoPayDelete(Long autoPayId, int autopayType, Map<String, String> headers) {

        AutoPayDeleteRequest.Params params = AutoPayDeleteRequest.Params.builder()
                .autopayType(autopayType)
                .autopayId(autoPayId)
                .build();

        AutoPayDeleteRequest request = AutoPayDeleteRequest.builder()
                .method("autopay.delete")
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
