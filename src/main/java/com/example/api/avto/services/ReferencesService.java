package com.example.api.avto.services;

import com.example.api.avto.models.references.*;
import com.example.click_avto_api.models.references.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.avto.MyAutoApiRequestSpecification;
import uz.click.click_avto_api.models.references.*;

import java.util.Map;

@Service
public class ReferencesService {
    @Autowired
    private MyAutoApiRequestSpecification reqSpec;

    public FaqGetResponse getFaqInfo(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/faq")
                .as(FaqGetResponse.class);
    }

    public TrafficLinesGetResponse trafficInfo(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/traffic-fines")
                .as(TrafficLinesGetResponse.class);
    }

    public MapTagsGetResponse mapTagsInfo(String token, int type) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .queryParam("type", type)
                .get("/v2/map/tags")
                .as(MapTagsGetResponse.class);
    }

    public MapResponse mapInfo(String token, Map<String, Object> params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .queryParams(params)
                .get("/v2/map")
                .as(MapResponse.class);
    }

    public MapResponse setFilterOnMap(String token, MapPostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/map")
                .as(MapResponse.class);
    }
}
