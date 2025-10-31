package com.example.tests.avto.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.avto.BaseModelResponse;
import com.example.api.avto.services.MyCarsService;
import com.example.tests.avto.MyAutoBaseTest;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;

public class UserTest extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();
    }

    @Test
    
    @DisplayName("[/v2/driver-license][GET] Получение инфо о водительский правах")
    void getDriverLicenseInfoWithNoCarTest() {
        step("Запрос на получение информации о водительских правах юзера", () -> {
            BaseModelResponse baseModelResponse = myCarsService.getDriverLicense(userToken);

            step("Проверка ответа", () -> {
                soft.assertThat(baseModelResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(baseModelResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/passport][GET] Получить паспорт пользователя")
    void getUserPassportTest() {
        step("Запрос на получение паспорта юзера", () -> {
            BaseModelResponse baseModelResponse = myCarsService.getUserPassport(userToken);

            step("Проверка ответа", () -> {
                soft.assertThat(baseModelResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(baseModelResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }
}