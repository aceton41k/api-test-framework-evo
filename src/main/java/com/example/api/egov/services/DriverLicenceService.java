package com.example.api.egov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.egov.EGovRequestSpecification;
import com.example.api.egov.models.driverLicense.DriverLicenceResponse;

import java.util.Map;

@Service
public class DriverLicenceService {

    @Autowired
    EGovRequestSpecification eGovRequestSpecification;

    public DriverLicenceResponse getDriverLicence(Map<String, String> request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/driverLicence/byPinflAndPassportData").body(request)
                                       .post().as(DriverLicenceResponse.class);
    }

}
