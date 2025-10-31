package com.example.api.egov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.egov.EGovRequestSpecification;
import com.example.api.egov.models.marriage.MarriageCertRequest;
import com.example.api.egov.models.marriage.MarriagePinflRequest;
import com.example.api.egov.models.marriage.MarriageRequest;
import com.example.api.egov.models.marriage.MarriageResponse;

@Service
public class MarriageService {

    @Autowired
    EGovRequestSpecification eGovRequestSpecification;

    public MarriageResponse getMarriageByFio(MarriageRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/marriageCertificate/byFullNameAndBirthDate")
                                       .body(request).post().as(MarriageResponse.class);
    }

    public MarriageResponse getMarriageByCert(MarriageCertRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/marriageCertificate/certNumber")
                                       .body(request).post().as(MarriageResponse.class);
    }

    public MarriageResponse getMarriageByPinfl(MarriagePinflRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/marriageCertificate/byPinfl")
                                       .body(request).post().as(MarriageResponse.class);
    }

}
