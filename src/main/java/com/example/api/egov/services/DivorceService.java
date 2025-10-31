package com.example.api.egov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.egov.EGovRequestSpecification;
import com.example.api.egov.models.divorce.DivorceCertRequest;
import com.example.api.egov.models.divorce.DivorcePinflRequest;
import com.example.api.egov.models.divorce.DivorceRequest;
import com.example.api.egov.models.divorce.DivorceResponse;

@Service
public class DivorceService {

    @Autowired
    EGovRequestSpecification eGovRequestSpecification;

    public DivorceResponse getDivorceByFio(DivorceRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/divorceCertificate/byFullNameAndBirthDate")
                                       .body(request).post().as(DivorceResponse.class);
    }

    public DivorceResponse getDivorceByCert(DivorceCertRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/divorceCertificate/certNumber").body(request).post()
                                       .as(DivorceResponse.class);
    }

    public <T> T getDivorceByPinfl(DivorcePinflRequest request, Class<T> responseType) {
        return eGovRequestSpecification.buildReqSpec().basePath("/divorceCertificate/byPinfl").body(request).post()
                                       .as(responseType);
    }

}
