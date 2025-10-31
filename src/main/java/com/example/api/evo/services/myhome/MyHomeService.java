package com.example.api.evo.services.myhome;

import java.util.Map;
import java.util.Optional;

import com.example.api.evo.models.myhome.*;
import com.example.evo.models.myhome.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.ResponseWithOkResult;
import uz.click.evo_api.models.myhome.*;
import com.example.utils.DateTimeUtil;

@Service
public class MyHomeService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public MyHomeListResponse getMyHomeList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("myhome.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(MyHomeListResponse.class);
    }

    public Optional<MyHomeListResponse.Result> getHomeById(Map<String, String> headers, Long homeId) {
        return this.getMyHomeList(headers).getResult().stream()
                .filter(home -> home.getId().equals(homeId))
                .findFirst();
    }
    /**
     * Если тут myHomeId будет 0 - то создается дом, если есть ид - то изменяется дом по ид
     */
    public MyHomeSaveResponse saveHomeById(MyHomeSaveRequest.Params params, Map<String, String> headers) {
        var request = MyHomeSaveRequest.builder()
                .method("myhome.save")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(MyHomeSaveResponse.class);
    }

    public ResponseWithOkResult deleteHomeById(MyHomeDeleteRequest.Params params, Map<String, String> headers) {
        var request = MyHomeDeleteRequest.builder()
                .method("myhome.delete")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public MyHomeGetPopularResponse getMyHomePopular(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("myhome.get.popular")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(MyHomeGetPopularResponse.class);
    }
}
