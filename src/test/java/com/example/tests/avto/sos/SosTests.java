package com.example.tests.avto.sos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.avto.models.sos.SosCategoriesGetResponse;
import com.example.api.avto.models.sos.SosServicesGetResponse;
import com.example.api.avto.services.MyCarsService;
import com.example.tests.avto.MyAutoBaseTest;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;

public class SosTests extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();
    }

    @Test
    
    @DisplayName("[v2/sos/categories][GET] Получить категории услуг")
    void getSosCategories() {
        step("Запрос на получение категорий услуг", () -> {

            SosCategoriesGetResponse sosCategoriesResp = myCarsService.getSosCategories(userToken);

            step("Проверка ответа", () -> {
                soft.assertThat(sosCategoriesResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(sosCategoriesResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }


    
    @DisplayName("[/v2/sos/categories/:id/services][GET] Получить список услуг по категории")
    @ParameterizedTest
    @CsvSource({
            "1, false",
            "2, false",
            "3, false",
            "4, true",
    })
    void getSosServices(int categoryId, boolean errorStatus) {
        step("Запрос на получение услуга по категориям", () -> {

            SosServicesGetResponse sosServicesResp = myCarsService.getSosServices(userToken, categoryId);

            step("Проверка ответа", () -> {
                soft.assertThat(sosServicesResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(sosServicesResp.getError()).as("Пришла ошибка " + errorStatus).isEqualTo(errorStatus);
            });
        });
    }
}