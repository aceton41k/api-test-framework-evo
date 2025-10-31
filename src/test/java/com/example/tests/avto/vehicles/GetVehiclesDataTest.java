package com.example.tests.avto.vehicles;

import com.example.api.avto.models.vehicles.VehicleColorUpdatePostRequest;
import com.example.api.avto.models.vehicles.VehicleUpdateOwnerInfoPostRequest;
import com.example.api.avto.models.vehicles.VehicleUpdateOwnerInfoPostResponse;
import com.example.api.avto.models.vehicles.VehiclesPostRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.avto.BaseModelResponse;
import uz.click.click_avto_api.models.vehicles.*;
import com.example.api.avto.services.MyCarsService;
import com.example.api.avto.services.VehiclesService;
import com.example.tests.avto.MyAutoBaseTest;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;

public class GetVehiclesDataTest extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    @Autowired
    VehiclesService vehiclesService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();

        var params = VehiclesPostRequest.Params.builder()
                .pinfl(DRIVER_PINFL)
                .techPassportSeries(TECH_PASSPORT_SERIES)
                .techPassportNumber(TECH_PASSPORT_NUMBER)
                .build();
        vehiclesService.addVehicles(userToken, params);
    }

    @Test
    
    @DisplayName("[/v2/vehicles][GET] Получение инфы о машинах")
    void getVehiclesTest() {
        step("Запрос на получение информации о машинах юзера", () -> {
            BaseModelResponse vehiclesResponse = vehiclesService.getVehicles(userToken);

            step("Проверка, что ответ не пустой", () -> {
                soft.assertThat(vehiclesResponse.getData()).as("Объект дата не должен быть пустым").isNotNull();
                soft.assertThat(vehiclesResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("/v2/vehicles/:id/docs][GET] Получить документы автомобиля")
    void getVehicleDocsTest() {
        step("Запрос на получение документов о машине юзера", () -> {
            BaseModelResponse vehiclesResponse = vehiclesService.getVehicleDocs(userToken, VEHICLE_ID);

            step("Проверка, что ответ не пустой", () -> {
                soft.assertThat(vehiclesResponse.getData()).as("Объект дата не должен быть пустым").isNotNull();
                soft.assertThat(vehiclesResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/vehicles/:id/docs/osago/update][POST] Обновить список полисов ОСАГО автомобиля")
    void updateOsagoListTest() {
        step("Запрос на обновление списка полисов", () -> {

            BaseModelResponse vehiclesResponse = vehiclesService.updateOsagoList(userToken, VEHICLE_ID);
            step("Проверка ответа", () -> {
                soft.assertThat(vehiclesResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });

        });
    }

    @Test
    
    @DisplayName("[/v2/vehicles/:id/as-default][POST] Выбрать автомобиль как автомобиль по умолчанию")
    void setVehicleAsDefaultTest() {
        step("Запрос на установки машины как дефолтная", () -> {

            vehiclesService.setVehicleAsDefault(userToken, 11);

            BaseModelResponse vehiclesResponse = vehiclesService.setVehicleAsDefault(userToken, VEHICLE_ID);
            step("Проверка ответа", () -> {
                soft.assertThat(vehiclesResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });

        });
    }

    @Test
    
    @DisplayName("[/v2/vehicles/:id/owner][POST] Обновить данные владельца автомобиля")
    void updateOwnerInfoTest() {
        step("Обновляет инфо водителя по ид машины. ", () -> {
            var params = VehicleUpdateOwnerInfoPostRequest.Params.builder()
                    .regionId(23) //заглушка
                    .districtId(2312) //заглушка
                    .build();

            VehicleUpdateOwnerInfoPostResponse updateInfoResp = vehiclesService.updateOwnerInfo(userToken,
                    params,
                    VEHICLE_ID);

            step("Проверка ответа", () -> {
                soft.assertThat(updateInfoResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });

        });
    }

    @Test
    
    @DisplayName("[/v2/vehicles/:id/owner][GET] Получить владельца автомобиля")
    void getVehicleOwnerTest() {
        step("Запрос на получение информации о владельце машины по ид машины", () -> {

            BaseModelResponse ownerResp = vehiclesService.getVehicleOwner(userToken, VEHICLE_ID);

            step("Проверка ответа", () -> {
                soft.assertThat(ownerResp.getError()).as("Пришла ошибка ").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/vehicles/:id/model/photos][GET] Получить список доступных фотографий для модели автомобил")
    void getPhotoListTest() {
        step("Запрос на получение доступных фото для машины", () -> {

            BaseModelResponse ownerResp = vehiclesService.getPhotoListTest(userToken, VEHICLE_ID);

            step("Проверка ответа", () -> {
                soft.assertThat(ownerResp.getError()).as("Пришла ошибка ").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/vehicles/:id/model/photos][POST] Обновить фотографию модели автомобиля")
    void setVehiclePhotoTest() {
        step("Запрос на установки цвета для машины", () -> {

            var params = VehicleColorUpdatePostRequest.Params.builder()
                    .vehiclePhotoId(3)
                    .build();

            BaseModelResponse vehiclesResponse = vehiclesService.setVehiclePhoto(userToken, VEHICLE_ID, params);
            step("Проверка ответа", () -> {
                soft.assertThat(vehiclesResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });

        });
    }

    @Test
    
    @DisplayName("[/v2/vehicles/sync][POST] Добавлять собственные автомобили автоматически")
    void addVehicleAutomatedTest() {
        step("Запрос на автоматическое добавление своей машины", () -> {

            BaseModelResponse vehiclesResponse = vehiclesService.addVehicleAutomated(userToken);
            step("Проверка ответа", () -> {
                soft.assertThat(vehiclesResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });

        });
    }
}
