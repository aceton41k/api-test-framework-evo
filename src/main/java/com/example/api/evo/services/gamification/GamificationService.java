package com.example.api.evo.services.gamification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.gamification.UserProgressPrivilegesGetResponse;
import com.example.api.evo.models.gamification.UserProgressStatusGetResponse;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class GamificationService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public UserProgressStatusGetResponse getUserStatusProgress(Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("user.progress.status.get")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(UserProgressStatusGetResponse.class);
    }

    public UserProgressPrivilegesGetResponse getUserPrivilegesProgress(Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("user.progress.privileges.get")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(UserProgressPrivilegesGetResponse.class);
    }
}
