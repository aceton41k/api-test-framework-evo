package com.example.tests.avto.osago;

import com.example.api.avto.models.osago.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.avto.BaseModelResponse;
import uz.click.click_avto_api.models.osago.*;
import com.example.api.avto.models.vehicles.VehiclesPostRequest;
import com.example.api.avto.services.MyCarsService;
import com.example.api.avto.services.OsagoService;
import com.example.api.avto.services.VehiclesService;
import com.example.tests.avto.MyAutoBaseTest;

import java.util.List;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;

public class GetOSAGOTests extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    @Autowired
    VehiclesService vehiclesService;
    @Autowired
    OsagoService osagoService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();

        var params = VehiclesPostRequest.Params.builder()
                .pinfl(COMPANY_INN)
                .techPassportSeries(TECH_PASSPORT_SERIES)
                .techPassportNumber(TECH_PASSPORT_NUMBER)
                .build();
        vehiclesService.addVehicles(userToken, params);
    }

    @Test
    
    @DisplayName("[v2/insurance/osago/form][GET] Получение данных автозаполнения полиса ОСАГО")
    void getOSAGOInfoWithCarTest() {
        step("Запрос на получение информации об ОСАГО юзера", () -> {

            Map<String, Object> queryParams = Map.of("vehicle_id", VEHICLE_ID, "driver_limit", 1);

            OSAGOGetResponse osagoResponse = osagoService.getOSAGO(userToken, queryParams);

            step("Проверка ответа", () -> {
                soft.assertThat(osagoResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(osagoResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/insurance/osago/kbm-price][POST] Получить стоимость КБМ ОСАГО")
    void getOSAGOKbmPriceWithCarTest() {
        step("Запрос на получение информации стоимости кбм ОСАГО у юзера", () -> {

            var params = OSAGOPKbmPricePostRequest.Params.builder()
                    .vehicleId(VEHICLE_ID)
                    .insuranceCompanyId(3)
                    .driverLimit(1)
                    .drivers(List.of(DRIVER_PINFL))
                    .build();
            OSAGOKbmPricePostResponse osagoKbmPriceResponse = osagoService.getOSAGOKbmPrice(userToken, params);
            step("Проверка ответа", () -> {
                soft.assertThat(osagoKbmPriceResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(osagoKbmPriceResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName(" [/v2/insurance/osago/price][POST] Получить стоимость ОСАГО")
    void getOSAGOPriceWithCarTest() {
        step("Запрос на получение информации стоимости ОСАГО у юзера", () -> {

            var params = OSAGOPricePostRequest.Params.builder().vehicleId(VEHICLE_ID).build();
            OSAGOPricePostResponse osagoPriceResponse = osagoService.getOSAGOPrice(userToken, params);
            step("Проверка ответа", () -> {
                soft.assertThat(osagoPriceResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(osagoPriceResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/insurance/osago/regions][GET] Получить справочник регионов")
    void getOSAGORegionsInfoTest() {
        step("Запрос на получение информацию о регионах для ОСАГО", () -> {

            OSAGORegionsResponse osagoRegResp = osagoService.getOsagoRegions(userToken);

            step("Проверка ответа", () -> {
                soft.assertThat(osagoRegResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(osagoRegResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/insurance/osago/relatives][GET] Получить справочник родственников")
    void getOSAGORelativesInfoTest() {
        step("Запрос на получение информацию о родственниках для ОСАГО", () -> {

            OSAGORelativesResponse osagoRelResp = osagoService.getOsagoRelatives(userToken);

            step("Проверка ответа", () -> {
                soft.assertThat(osagoRelResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(osagoRelResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/insurance/osago/driver][POST] Добавить водителя")
    void addDriverInOsagoTest() {
        step("Запрос на добавления водителя в ОСАГО", () -> {

            var params = OSAGODriverPostRequest.Params.builder()
                    .relativeId(5)
                    .passportSeries("AB")
                    .passportNumber(7510900)
                    .birthDate("25.03.1993")
                    .build();

            OSAGODriverPostResponse addDriverResp = osagoService.addDriverInOsago(userToken, params);
            step("Проверка ответа", () -> {
                soft.assertThat(addDriverResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(addDriverResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/insurance/osago/check][POST] Проверить статус страхового полиса ОСАГО")
    void checkStatusOsagoTest() {
        step("Запрос на проверку статуса ОСАГО", () -> {

            var params = OSAGOCheckStatusPostRequest.Params.builder()
                    .checkId(1041909) //заглушка. ИД страхового полиса
                    .build();

            OSAGOCheckStatusPostResponse addDriverResp = osagoService.checkStatusOsago(userToken, params);
            step("Проверка ответа", () -> {
                soft.assertThat(addDriverResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(addDriverResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/insurance/osago/regions][GET] Получить справочник регионов")
    void getRegionsListTest() {
        step("Запрос на получение списка регионов", () -> {

            BaseModelResponse osagoRelResp = osagoService.getRegionsList(userToken);

            step("Проверка ответа", () -> {
                soft.assertThat(osagoRelResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(osagoRelResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

}
