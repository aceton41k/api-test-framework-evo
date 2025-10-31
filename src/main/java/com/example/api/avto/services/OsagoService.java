package com.example.api.avto.services;

import com.example.api.avto.models.osago.*;
import com.example.click_avto_api.models.osago.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.avto.BaseModelResponse;
import com.example.api.avto.MyAutoApiRequestSpecification;
import uz.click.click_avto_api.models.osago.*;

import java.util.Map;

@Service
public class OsagoService {
    @Autowired
    private MyAutoApiRequestSpecification reqSpec;

    public OSAGOGetResponse getOSAGO(String token, Map<String, Object> params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .queryParams(params)
                .get("/v2/insurance/osago/form")
                .as(OSAGOGetResponse.class);
    }

    public OSAGOPricePostResponse getOSAGOPrice(String token, OSAGOPricePostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/insurance/osago/price")
                .as(OSAGOPricePostResponse.class);
    }

    public OSAGOKbmPricePostResponse getOSAGOKbmPrice(String token, OSAGOPKbmPricePostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/insurance/osago/kbm-price")
                .as(OSAGOKbmPricePostResponse.class);
    }
    public OSAGORegionsResponse getOsagoRegions(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/insurance/osago/regions")
                .as(OSAGORegionsResponse.class);
    }

    public OSAGORelativesResponse getOsagoRelatives(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/insurance/osago/relatives")
                .as(OSAGORelativesResponse.class);
    }

    public OSAGODriverPostResponse addDriverInOsago(String token, OSAGODriverPostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/insurance/osago/driver")
                .as(OSAGODriverPostResponse.class);
    }

    public OSAGOCheckStatusPostResponse checkStatusOsago(String token, OSAGOCheckStatusPostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/insurance/osago/check")
                .as(OSAGOCheckStatusPostResponse.class);
    }

    public BaseModelResponse getRegionsList(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/insurance/osago/regions")
                .as(BaseModelResponse.class);
    }
}
