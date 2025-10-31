package com.example.api.gosuslugi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.gosuslugi.GovApiRequestSpecification;
import com.example.api.gosuslugi.models.services.GetCategoriesWithServicesResponse;

import java.util.HashMap;
import java.util.Map;

@Service
public class GovServices {
    @Autowired
    GovApiRequestSpecification reqSpec;

    public GetCategoriesWithServicesResponse[] getServices(Map<String, String> headers, String type, Integer id) {
        var params = new HashMap<String, Object>();
        if (type != null) {
            params.put("type", type);
        }
        if (id != null) {
            params.put("id", id);
        }
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .params(params)
                .get("/getCategoriesWithServices")
                .as(GetCategoriesWithServicesResponse[].class);


    }
}
