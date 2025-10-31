package com.example.api.evo.services.big_cashback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.big_cashback.BigCashbackIndoorServiceListRequest;
import com.example.api.evo.models.indoor.IndoorServiceListResponse;
import com.example.api.evo.models.services.CategoryListResponse;
import com.example.utils.DateTimeUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

@Service
public class BigCashbackService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public CategoryListResponse getBcCategoryList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("promo.bigcashback.category.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(CategoryListResponse.class);
    }

    public CategoryListResponse getBcIndoorCategoryList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("promo.bigcashback.indoor.category.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(CategoryListResponse.class);
    }

    public IndoorServiceListResponse getBcServiceList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("promo.bigcashback.service.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(IndoorServiceListResponse.class);
    }

    public IndoorServiceListResponse getBcIndoorServiceList(
            BigCashbackIndoorServiceListRequest.Params params, Map<String, String> headers) {
        var request = BigCashbackIndoorServiceListRequest.builder()
                .method("promo.bigcashback.indoor.service.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(IndoorServiceListResponse.class);
    }

    public IndoorServiceListResponse getBcFeaturedServiceList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("promo.bigcashback.featured.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(IndoorServiceListResponse.class);
    }

    public IndoorServiceListResponse getBcVipServiceList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("promo.bigcashback.vip.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(IndoorServiceListResponse.class);
    }

    public void verifyCategoryImagesAreReachable(CategoryListResponse response) {
        List<String> imageUrls = response.getResult().stream()
                .map(CategoryListResponse.Result::getImage)
                .collect(Collectors.toList());
        imageUrls.stream().forEach(url -> {
            given().when().get(url).then().statusCode(200);
        });
    }
}
