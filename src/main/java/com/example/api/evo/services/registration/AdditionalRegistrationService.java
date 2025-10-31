package com.example.api.evo.services.registration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.registration.CallIvrRequest;
import com.example.api.evo.models.registration.GetRegionListResponse;
import com.example.api.evo.models.registration.PinResetRequest;
import com.example.api.evo.models.registration.PinResetResponse;
import com.example.utils.DateTimeUtil;

@Service
public class AdditionalRegistrationService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public PinResetResponse pinResetRequest(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("pin.reset.request.new")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(PinResetResponse.class);
    }

    public PinResetResponse pinReset(PinResetRequest.Params params, Map<String, String> headers) {
        var request = PinResetRequest.builder()
                .method("pin.reset")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(PinResetResponse.class);
    }

    public GetRegionListResponse getRegionList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("get.region.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetRegionListResponse.class);
    }

    public ResponseWithOkResult callIvr(CallIvrRequest.Params params, Map<String, String> headers) {
        var request = CallIvrRequest.builder()
                .method("call.ivr")
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
