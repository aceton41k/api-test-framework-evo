package com.example.api.travel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.travel.UnitTravelRequestSpecification;
import com.example.api.travel.constants.UnitTravelData;
import com.example.api.travel.models.unit_travel.UnitTravelFlightSearchResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class UnitTravelService {
    @Autowired private UnitTravelRequestSpecification spec;

    public List<UnitTravelFlightSearchResponse> unitTravelSearchService(Map<String, ?> qp) {
        return Arrays.asList(
                spec.req()
                        .basePath("/agent/v2/flights/availability")
                        .queryParams(qp)
                        .queryParam("apikey", UnitTravelData.apiKey())
                        .get()
                        .as(UnitTravelFlightSearchResponse[].class)
        );
    }
}
