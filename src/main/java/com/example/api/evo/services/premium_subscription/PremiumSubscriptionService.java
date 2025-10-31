package com.example.api.evo.services.premium_subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.premium_subscription.PremiumTariffListResponse;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class PremiumSubscriptionService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public PremiumTariffListResponse getPremiumTariffList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("premium.tariff.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(PremiumTariffListResponse.class);
    }
}
