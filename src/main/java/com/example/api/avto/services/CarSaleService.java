package com.example.api.avto.services;

import com.example.api.avto.models.car_sale.*;
import com.example.click_avto_api.models.car_sale.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.avto.MyAutoApiRequestSpecification;
import uz.click.click_avto_api.models.car_sale.*;

@Service
public class CarSaleService {

    @Autowired
    private MyAutoApiRequestSpecification reqSpec;

    public CarSaleRegionsGetResponse getCarSaleRegions(String token) {

        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/car-sale/regions")
                .as(CarSaleRegionsGetResponse.class);
    }

    public CarSaleMarksAndBrandsGetResponse getMarksAndBrands(String token, Integer regionId) {

        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .queryParam("region_id", regionId)
                .get("/v2/car-sale/cars")
                .as(CarSaleMarksAndBrandsGetResponse.class);
    }

    public CarSaleConfigurationsGetResponse getCarSaleConfigs(String token, int markId) {

        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/car-sale/cars/"+ markId +"/configurations")
                .as(CarSaleConfigurationsGetResponse.class);
    }

    public CarSaleConfigsOffersGetResponse getCarOffers(String token, Integer markId) {

        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/car-sale/configurations/"+markId+"/offers")
                .as(CarSaleConfigsOffersGetResponse.class);
    }

    public CarSaleOrderPostResponse addOrderCarSale(String token, CarSaleOrderPostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/car-sale/orders")
                .as(CarSaleOrderPostResponse.class);
    }
}
