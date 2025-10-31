package com.example.api.gosuslugi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.gosuslugi.GovApiRequestSpecification;
import com.example.api.gosuslugi.models.faq.GetFaqCategoriesResponse;

import java.util.Map;

@Service
public class GovFaqCategories {
    @Autowired
    GovApiRequestSpecification reqSpec;

    public GetFaqCategoriesResponse[] getFaq(Map<String, String> token) {
        return reqSpec.getRequestSpecification()
                .headers(token)
                .get("/faq_categories")
                .as(GetFaqCategoriesResponse[].class);
    }
}
