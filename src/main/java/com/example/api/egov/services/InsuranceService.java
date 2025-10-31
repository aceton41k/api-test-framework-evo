package com.example.api.egov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.egov.EGovRequestSpecification;
import com.example.api.egov.models.insurance.InsurancePolicyResponse;

import java.util.Map;

@Service
public class InsuranceService {

    @Autowired
    EGovRequestSpecification eGovRequestSpecification;

    public InsurancePolicyResponse getInsurancePolicyByPinfl(Map<String, String > pinfl) {
        return eGovRequestSpecification.buildReqSpec().basePath("/insurancePolicy/byPinfl").body(pinfl)
                                       .post().as(InsurancePolicyResponse.class);
    }

    public InsurancePolicyResponse getInsurancePolicyByInn(Map<String, String> inn) {
        return eGovRequestSpecification.buildReqSpec().basePath("/insurancePolicy/byInn").body(inn).post()
                                       .as(InsurancePolicyResponse.class);
    }

    public <T>T getInsurancePolicyByGovNumber(Map<String, String> govNumber,  Class<T> clazz) {
        return eGovRequestSpecification.buildReqSpec().basePath("/insurancePolicy/byGovNumber").body(govNumber).post()
                                       .as(clazz);
    }

}
