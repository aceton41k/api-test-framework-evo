package com.example.api.evo.services.misc;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.misc.FormSyncRequest;
import com.example.utils.DateTimeUtil;

@Service
public class FormSyncService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public Response getFormSync(FormSyncRequest.Params params) {
        var request = FormSyncRequest.builder()
                .method("form.sync")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification().body(request).post();
    }

    public io.restassured.response.Response getServiceImages() {
        var request = RequestWithoutParams.builder()
                .method("service.images")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification().body(request).post();
    }

    public Response getCategoryImages() {
        var request = RequestWithoutParams.builder()
                .method("category.images")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification().body(request).post();
    }
}
