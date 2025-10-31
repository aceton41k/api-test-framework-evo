package com.example.api.evo.services.stories;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.news.NewsGetRequest;
import com.example.api.evo.models.news.NewsGetResponse;
import com.example.api.evo.models.news.NewsListRequest;
import com.example.utils.DateTimeUtil;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Service
public class StoriesService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    @NotNull
    public <T>T getStoriesList(NewsListRequest.Params params,Class<T> responseType, Map<String, String> headers) {
        var request = NewsListRequest.builder()
                .method("stories.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    @NotNull
    public NewsGetResponse getStoryById(NewsGetRequest.Params params, Map<String, String> headers) {
        var request = NewsGetRequest.builder()
                .method("stories.get")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(NewsGetResponse.class);
    }
}
