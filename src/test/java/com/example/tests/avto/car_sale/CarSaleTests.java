package com.example.tests.avto.car_sale;

import com.example.api.avto.models.car_sale.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import uz.click.click_avto_api.models.car_sale.*;
import com.example.api.avto.services.CarSaleService;
import com.example.api.avto.services.MyCarsService;
import com.example.tests.avto.MyAutoBaseTest;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;

public class CarSaleTests extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    @Autowired
    CarSaleService carSaleService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();
    }

    @Test
    
    @DisplayName("[/v2/car-sale/regions][GET] Получить регионы")
    void getCarSaleRegions() {
        step("Запрос на получение регионов", () -> {

            CarSaleRegionsGetResponse carSaleRegResp = carSaleService.getCarSaleRegions(userToken);

            step("Проверка ответа", () -> {
                soft.assertThat(carSaleRegResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(carSaleRegResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }


    
    @DisplayName("[/v2/car-sale/cars][GET] Получить марки и модели")
    @ParameterizedTest
    @CsvSource({
            "1, false",
            "5, false",
            "6, false",
            "13, false",
            " , false"
    })
    void getCarSaleMarksAndBrands(Integer regionId, boolean errorStatus) {
        step("Запрос на получение машин по id региона", () -> {


            CarSaleMarksAndBrandsGetResponse brandsAndMarksResp = carSaleService.getMarksAndBrands(userToken, regionId);

            step("Проверка ответа", () -> {
                soft.assertThat(brandsAndMarksResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(brandsAndMarksResp.getError())
                        .as("Пришла ошибка " + errorStatus)
                        .isEqualTo(errorStatus);
            });
        });
    }

    
    @DisplayName("[/v2/car-sale/cars/:id/configurations][GET] Получить комплектации модели")
    @ParameterizedTest
    @CsvSource({
            "5, false",
            "6, false"
    })
    void getCarSaleConfigs(int markId, boolean errorStatus) {
        step("Запрос на получение комплектации модели", () -> {

            CarSaleConfigurationsGetResponse carConfigsResp = carSaleService.getCarSaleConfigs(userToken, markId);

            step("Проверка ответа", () -> {
                soft.assertThat(carConfigsResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(carConfigsResp.getError()).as("Пришла ошибка " + errorStatus).isEqualTo(errorStatus);
            });
        });
    }

    
    @DisplayName("[/v2/car-sale/configurations/:id/offers][GET] Получить предложения по комплектации")
    @ParameterizedTest
    @CsvSource({
            "5, false",
            "6, false"
    })
    void getCarOffers(int configId, boolean errorStatus) {
        step("Запрос на получение номера оффера", () -> {

            CarSaleConfigsOffersGetResponse carConfigsResp = carSaleService.getCarOffers(userToken, configId);

            step("Проверка ответа", () -> {
                soft.assertThat(carConfigsResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(carConfigsResp.getError()).as("Пришла ошибка " + errorStatus).isEqualTo(errorStatus);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/car-sale/orders][POST] Создать заявку на продажу авто")
    void addOrderCarSaleTest() {
        step("Запрос на создание оффера на продажу", () -> {
            var params = CarSaleOrderPostRequest.Params.builder()
                    .offerId(10)
                    .build();
            CarSaleOrderPostResponse orderCarSale = carSaleService.addOrderCarSale(userToken, params);
            step("Проверка ответа", () -> {
                soft.assertThat(orderCarSale.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(orderCarSale.getError()).as("Пришла ошибка").isEqualTo(false);
            });

        });
    }
}
