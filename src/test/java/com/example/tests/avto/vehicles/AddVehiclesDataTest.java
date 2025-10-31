package com.example.tests.avto.vehicles;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.avto.BaseModelResponse;
import com.example.api.avto.models.vehicles.VehiclesPostRequest;
import com.example.api.avto.services.MyCarsService;
import com.example.api.avto.services.VehiclesService;
import com.example.tests.avto.MyAutoBaseTest;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;
import static com.example.api.avto.constants.Vehicles.VEHICLE_ID;

public class AddVehiclesDataTest extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    @Autowired
    VehiclesService vehiclesService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();

        vehiclesService.deleteVehicleById(userToken, VEHICLE_ID);
    }

    
    @DisplayName("[v2/vehicles][POST] Добавление автомобиля в ручную")
    @ParameterizedTest
    @CsvSource({
            "31602998660024, AAG, 2222222, false",
            "0, AAG, 0645180, true"
    })
    void addVehiclesTest(String pinfl, String techPassportSeries, String techPassportNumber, Boolean errorStatus) {
        step("Запрос на добавление машины к юзеру", () -> {
            var params = VehiclesPostRequest.Params.builder()
                    .pinfl(pinfl)
                    .techPassportSeries(techPassportSeries)
                    .techPassportNumber(techPassportNumber)
                    .build();
            BaseModelResponse vehiclesResponse = vehiclesService.addVehicles(userToken, params);
            step("Проверка ответа", () -> {
                soft.assertThat(vehiclesResponse.getError()).as("Пришла ошибка").isEqualTo(errorStatus);
            });

        });
    }
}
