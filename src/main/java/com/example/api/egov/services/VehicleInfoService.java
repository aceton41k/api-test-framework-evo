package com.example.api.egov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.egov.EGovRequestSpecification;
import com.example.api.egov.models.vehicle_info.VehicleInfoResponse;

import java.util.Map;

@Service
public class VehicleInfoService {

    @Autowired
    EGovRequestSpecification eGovRequestSpecification;

    public VehicleInfoResponse getVehicleInfoByTin(Map<String, String> request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/vehicleInfo/byTin").body(request).post()
                                       .as(VehicleInfoResponse.class);
    }

    public VehicleInfoResponse getVehicleByPlateNumber(Map<String, String> request) {
        return eGovRequestSpecification.buildReqSpec().basePath("/vehicleInfo/byPlateNumber").body(request).post()
                                       .as(VehicleInfoResponse.class);
    }

    public <T> T getVehicleInfoByPinfl(Map<String, String> request, Class<T> responseType) {
        return eGovRequestSpecification.buildReqSpec().basePath("/vehicleInfo/byPinfl").body(request).post()
                                       .as(responseType);
    }

}
