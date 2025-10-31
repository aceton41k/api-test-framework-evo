package com.example.api.travel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.travel.TravelMyAgentRequestSpecification;
import com.example.api.travel.models.my_agent.MyAgentFlightSearchResponse;

import java.util.Map;

@Service
public class TravelMyAgentService {
    @Autowired
    private TravelMyAgentRequestSpecification spec;

    public MyAgentFlightSearchResponse myAgentSearchService(Map<String, ?> qp) {
        return spec.req()
                .basePath("/api/my-agent/search-for-recommendations-fixed")
                .queryParams(qp)
                .get()
                .as(MyAgentFlightSearchResponse.class);
    }
}
