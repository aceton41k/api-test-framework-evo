package com.example.api.evo.services.indoor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.indoor.IndoorServiceListRequest;
import com.example.api.evo.models.indoor.IndoorServiceListResponse;
import com.example.api.evo.models.services.CategoryListResponse;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class IndoorListService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public CategoryListResponse getIndoorCategoryList(Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("indoor.category.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(CategoryListResponse.class);
    }

    public IndoorServiceListResponse getIndoorServiceList(IndoorServiceListRequest.Params params, Map<String, String> headers) {
        IndoorServiceListRequest request = IndoorServiceListRequest.builder()
                .method("indoor.service.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(IndoorServiceListResponse.class);
    }
}
