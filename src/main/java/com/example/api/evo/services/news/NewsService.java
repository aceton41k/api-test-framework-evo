package com.example.api.evo.services.news;

import com.example.api.evo.models.news.*;
import com.example.evo.models.news.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import uz.click.evo_api.models.news.*;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class NewsService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public NewsListResponse getNewsList(NewsListRequest.Params params, Map<String, String> headers) {
        var request = NewsListRequest.builder()
                .method("news.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(NewsListResponse.class);
    }

    public NewsGetResponse getNewsById(NewsGetRequest.Params params, Map<String, String> headers) {
        var request = NewsGetRequest.builder()
                .method("news.get")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(NewsGetResponse.class);
    }

    public NewsViewResponse viewNewsById(NewsGetRequest.Params params, Map<String, String> headers) {
        var request = NewsGetRequest.builder()
                .method("news.view")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(NewsViewResponse.class);
    }

    public <T> T likeNewsById(NewsGetRequest.Params params, Class<T> responseType, Map<String, String> headers) {
        var request = NewsGetRequest.builder()
                .method("news.like")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public <T> T unlikeNewsById(NewsGetRequest.Params params, Class<T> responseType, Map<String, String> headers) {
        var request = NewsGetRequest.builder()
                .method("news.unlike")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }
}
