package com.example.api.avto.models.car_sale;

import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class CarSaleRegionsGetResponse extends BaseModelResponse<List<CarSaleRegionsGetResponse.Data>> {

    @Getter
    public static class Data {
        private Integer id;
        private String name;
        private Boolean selected;
    }
}
