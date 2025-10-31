package com.example.api.evo.services.settings;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.settings.DeviceListResponse;
import com.example.api.evo.models.settings.RevokeDeviceRequest;
import com.example.utils.DateTimeUtil;

@Service
public class DeviceListService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public DeviceListResponse getDeviceList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("device.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(DeviceListResponse.class);
    }

    public ResponseWithOkResult revokeDevice(RevokeDeviceRequest.Params params, Map<String, String> headers) {
        var request = RevokeDeviceRequest.builder()
                .method("device.revoke")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult revokeAllDevice(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("device.revoke.all")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }
}
