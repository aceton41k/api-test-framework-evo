package com.example.api.evo.services.promo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.promo.AdsSliderGetResponse;
import uz.click.evo_api.models.reports.*;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class PromoService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public AdsSliderGetResponse adsSliderGet(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("ads.slider.get")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(AdsSliderGetResponse.class);
    }

}
