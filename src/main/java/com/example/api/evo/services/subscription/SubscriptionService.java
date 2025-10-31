package com.example.api.evo.services.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.subscription.SubscriptionListRequest;
import com.example.api.evo.models.subscription.SubscriptionListResponse;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class SubscriptionService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public SubscriptionListResponse getSubscriptionList(SubscriptionListRequest.Params params, Map<String, String> headers) {
        var request = SubscriptionListRequest.builder()
                .method("subscription.list")
                .params(params)
                .id(DateTimeUtil.getCurrentTimestamp())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(SubscriptionListResponse.class);
    }
}