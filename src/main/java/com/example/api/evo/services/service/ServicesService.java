package com.example.api.evo.services.service;

import java.util.Map;

import com.example.api.evo.models.services.*;
import com.example.evo.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.ResponseWithOkResult;
import uz.click.evo_api.models.services.*;
import com.example.utils.DateTimeUtil;

@Service
public class ServicesService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public CategoryListResponse getCategoryList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("category.list")
                .params(RequestWithoutParams.Params.builder().build())
                .id(DateTimeUtil.getCurrentTimestamp())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(CategoryListResponse.class);
    }

    public ServiceDataResponse getServiceData(ServiceDataRequest.Params params, Map<String, String> headers) {
        var request = ServiceDataRequest.builder()
                .method("service.data")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ServiceDataResponse.class);
    }

    public ServiceListResponse getServiceList(ServiceListRequest.Params params, Map<String, String> headers) {
        var request = ServiceListRequest.builder()
                .method("service.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ServiceListResponse.class);
    }

    public ServiceListResponse getCommunalServiceList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("communal.service.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ServiceListResponse.class);
    }

    public ServiceMetaResponse getServiceMeta(ServiceDataRequest.Params params, Map<String, String> headers) {
        var request = ServiceDataRequest.builder()
                .method("service.meta")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ServiceMetaResponse.class);
    }

    public ServiceSearchResponse searchServices(ServiceSearchRequest.Params params, Map<String, String> headers) {
        var request = ServiceSearchRequest.builder()
                .method("service.search")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ServiceSearchResponse.class);
    }

    public ResponseWithOkResult trackSearchKeyword(
            TrackSearchKeywordRequest.Params params, Map<String, String> headers) {
        var request = TrackSearchKeywordRequest.builder()
                .method("track.search.keyword")
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
