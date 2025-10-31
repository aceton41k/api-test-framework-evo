package com.example.api.avto.services;

import com.example.api.avto.models.vehicles.*;
import com.example.click_avto_api.models.vehicles.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.avto.BaseModelResponse;
import com.example.api.avto.MyAutoApiRequestSpecification;
import uz.click.click_avto_api.models.vehicles.*;

@Service
public class VehiclesService {
    @Autowired
    private MyAutoApiRequestSpecification reqSpec;

    public BaseModelResponse getVehicles(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/vehicles")
                .as(BaseModelResponse.class);
    }

    public BaseModelResponse addVehicles(String token, VehiclesPostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/vehicles")
                .as(BaseModelResponse.class);
    }

    public BaseModelResponse deleteVehicleById(String token, int vehicleId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .delete("/v2/vehicles/"+vehicleId)
                .as(BaseModelResponse.class);
    }
    public VehicleTechPassportResponse getVehicleTechPassport(String token, int vehicleId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/vehicles/"+vehicleId+"/tech-passport")
                .as(VehicleTechPassportResponse.class);
    }

    public BaseModelResponse getVehicleOwner(String token, int vehicleId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/vehicles/"+vehicleId+"/owner")
                .as(BaseModelResponse.class);
    }

    public VehicleUpdateOwnerInfoPostResponse updateOwnerInfo(String token, VehicleUpdateOwnerInfoPostRequest.Params params, int vehicleId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/vehicles/"+vehicleId+"/owner")
                .as(VehicleUpdateOwnerInfoPostResponse.class);
    }

    public VehiclesOsagoPoliciesGetResponse getVehicleOsagoPolicies(String token, int vehicleId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/vehicles/"+vehicleId+ "/osago-policies")
                .as(VehiclesOsagoPoliciesGetResponse.class);
    }

    public BaseModelResponse getVehicleDocs(String token, int vehicleId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/vehicles/"+vehicleId+"/docs")
                .as(BaseModelResponse.class);
    }

    public BaseModelResponse updateOsagoList(String token, int vehicleId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .post("/v2/vehicles/"+vehicleId+"/docs/osago/update")
                .as(BaseModelResponse.class);
    }

    public BaseModelResponse setVehicleAsDefault(String token, int vehicleId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .post("/v2/vehicles/"+vehicleId+"/as-default")
                .as(BaseModelResponse.class);
    }

    public BaseModelResponse getPhotoListTest(String token, int vehicleId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/vehicles/"+vehicleId+"/model/photos")
                .as(BaseModelResponse.class);
    }

    public BaseModelResponse setVehiclePhoto(String token, int vehicleId, VehicleColorUpdatePostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/vehicles/"+vehicleId+"/model/photos")
                .as(BaseModelResponse.class);
    }

    public BaseModelResponse addVehicleAutomated(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .post("/v2/vehicles/sync")
                .as(BaseModelResponse.class);
    }
}
