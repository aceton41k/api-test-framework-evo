package com.example.api.egov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.egov.EGovRequestSpecification;
import com.example.api.egov.models.birth.BirthCertRequest;
import com.example.api.egov.models.birth.BirthPinflRequest;
import com.example.api.egov.models.birth.BirthRequest;
import com.example.api.egov.models.birth.BirthResponse;

@Service
public class BirthService {

    @Autowired
    EGovRequestSpecification eGovRequestSpecification;

    public BirthResponse getBirthByFio(BirthRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/birthCertificate/byFullNameAndBirthDate")
                                       .body(request).post().as(BirthResponse.class);
    }

    public BirthResponse getBirthByCert(BirthCertRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/birthCertificate/certNumber").body(request).post()
                                       .as(BirthResponse.class);
    }

    public <T> T getBirthByPinfl(BirthPinflRequest request, Class<T> responseType) {
        return eGovRequestSpecification.buildReqSpec().basePath("/birthCertificate/byPinfl").body(request).post()
                                       .as(responseType);
    }

}
