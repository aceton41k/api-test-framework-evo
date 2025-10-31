package com.example.tests.avto.vehicles;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.avto.BaseModelResponse;
import com.example.api.avto.models.vehicles.VehiclesPostRequest;
import com.example.api.avto.services.MyCarsService;
import com.example.api.avto.services.VehiclesService;
import com.example.tests.avto.MyAutoBaseTest;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;
import static com.example.api.avto.constants.Vehicles.TECH_PASSPORT_NUMBER;

public class DeleteVehiclesTest extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    @Autowired
    VehiclesService vehiclesService;
    String userToken;

    @BeforeAll
    void setup(){userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();}

    @BeforeEach
    void setupVehicle(){
        var params = VehiclesPostRequest.Params.builder()
                .pinfl(DRIVER_PINFL)
                .techPassportSeries(TECH_PASSPORT_SERIES)
                .techPassportNumber(TECH_PASSPORT_NUMBER)
                .build();
        vehiclesService.addVehicles(userToken, params);
    }

    @Test
    
    @DisplayName("[v2/vehicles/id][DELETE] Удаление автомобиля по ид")
    void deleteVehicleByIdTest(){
        step("Запрос на удаление автомобиля юзера по ид", () -> {
            BaseModelResponse vehicleDeleteResponse = vehiclesService.deleteVehicleById(userToken, VEHICLE_ID);

            step("Проверка ответа", () -> {
                soft.assertThat(vehicleDeleteResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }
}
