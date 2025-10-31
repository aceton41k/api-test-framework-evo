package com.example.api.evo.services.misc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.misc.SafeModeResponse;
import com.example.api.evo.models.misc.VersionInfoRequest;
import com.example.api.evo.models.misc.VersionInfoResponse;
import com.example.utils.DateTimeUtil;

@Service
public class MiscService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public VersionInfoResponse getVersionInfo(VersionInfoRequest.Params params, Map<String, String> headers) {
        var request = VersionInfoRequest.builder()
                .method("version.info")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(VersionInfoResponse.class);
    }

    public SafeModeResponse getSafeMode() {
        return reqSpec.getRequestSpecification().get("url").as(SafeModeResponse.class);
    }
}
