package com.example.api.egov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.egov.EGovRequestSpecification;
import com.example.api.egov.models.passport_info.PassportInfoPinBirthRequest;
import com.example.api.egov.models.passport_info.PassportInfoPinSerialBirthRequest;
import com.example.api.egov.models.passport_info.PassportInfoPinSerialRequest;
import com.example.api.egov.models.passport_info.PassportInfoResponse;

@Service
public class PassportInfoService {

    @Autowired
    EGovRequestSpecification eGovRequestSpecification;

    public PassportInfoResponse getPassportInfoByPinflSerialNumber(PassportInfoPinSerialRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/passportInfo/byPinflAndSeriaNumber")
                                       .body(request).post().as(PassportInfoResponse.class);
    }

    public PassportInfoResponse getPassportInfoByPinflSerialNumberBirth(PassportInfoPinSerialBirthRequest request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/passportInfo/byPinflAndSeriaNumberAndBirthDate")
                                       .body(request).post().as(PassportInfoResponse.class);
    }

    public <T> T getPassportInfoByPinAndBirth(PassportInfoPinBirthRequest request, Class<T> responseType) {
        return eGovRequestSpecification.buildReqSpec().basePath("/passportInfo/byPinflAndBirthDate").body(request).post()
                                       .as(responseType);
    }

}
