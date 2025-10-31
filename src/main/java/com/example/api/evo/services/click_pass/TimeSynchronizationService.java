package com.example.api.evo.services.click_pass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.click_pass.TimeSynchronizationRequest;
import com.example.api.evo.models.click_pass.TimeSynchronizationResponse;
import com.example.utils.DateTimeUtil;

@Service
public class TimeSynchronizationService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public TimeSynchronizationResponse timeSync(TimeSynchronizationRequest.Params params) {
        TimeSynchronizationRequest request = TimeSynchronizationRequest.builder()
                .method("time.sync")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification().body(request).post().as(TimeSynchronizationResponse.class);
    }
}
