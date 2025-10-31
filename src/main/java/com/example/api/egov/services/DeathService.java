package com.example.api.egov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.egov.EGovRequestSpecification;
import com.example.api.egov.models.death.DeathCertRequest;
import com.example.api.egov.models.death.DeathPinflRequest;
import com.example.api.egov.models.death.DeathRequest;
import com.example.api.egov.models.death.DeathResponse;

@Service
public class DeathService {

    @Autowired
    EGovRequestSpecification eGovRequestSpecification;

    public DeathResponse getDeathByFio(DeathRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/deathCertificate/byFullNameAndBirthDate")
                                       .body(request).post().as(DeathResponse.class);
    }

    public DeathResponse getDeathByCert(DeathCertRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/deathCertificate/certNumber").body(request).post()
                                       .as(DeathResponse.class);
    }

    public <T> T getDeathByPinfl(DeathPinflRequest request, Class<T> responseType) {
        return eGovRequestSpecification.buildReqSpec().basePath("/deathCertificate/byPinfl").body(request).post()
                                       .as(responseType);
    }

}
