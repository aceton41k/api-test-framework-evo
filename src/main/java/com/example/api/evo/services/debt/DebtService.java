package com.example.api.evo.services.debt;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.debt.GetDebtListResponse;

import com.example.utils.DateTimeUtil;

@Service
public class DebtService {
    @Autowired
    EvoApiRequestSpecification requestSpecification;

    public GetDebtListResponse getDebtList(Map<String, String> headers){

        var request = RequestWithoutParams.builder()
                .method("debt.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();

        return requestSpecification
                .getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetDebtListResponse.class);

    }
}
