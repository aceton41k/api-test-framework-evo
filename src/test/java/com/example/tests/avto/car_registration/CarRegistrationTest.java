package com.example.tests.avto.car_registration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.avto.models.car_registration.CarRegInvoicePostRequest;
import com.example.api.avto.models.car_registration.CarRegInvoicePostResponse;
import com.example.api.avto.models.car_registration.CarRegInvoiceStatusPostRequest;
import com.example.api.avto.services.CarRegistrationService;
import com.example.api.avto.services.MyCarsService;
import com.example.tests.avto.MyAutoBaseTest;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;

public class CarRegistrationTest extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    @Autowired
    CarRegistrationService carRegistrationService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();
    }

    @Test
    
    @DisplayName("[/v2/car-registration/parse-sms][PSOT] Регистрация авто. Парсинг SMS и создание инвойсов")
    void addCarRegInvoice() {
        step("Запрос добавление инвойса", () -> {
            var params = CarRegInvoicePostRequest.Params.builder()
                    .sms("Hurmatli fuqaro Sizning transport vositangizga ko'rsatiladigan xizmat uchun to'lov shakllantirildi: " +
                            "33429088367287 - 262,500.00 92719649940112 - 37,500.00 50918183996984 - 37,500.00")
                    .build();
            CarRegInvoicePostResponse carRegInvoiceResp = carRegistrationService.addCarRegInvoice(userToken, params);
            step("Проверка ответа", () -> {
                soft.assertThat(carRegInvoiceResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(carRegInvoiceResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });

        });
    }

    @Disabled("Флаки тест. Нужно зашить в коде определенную id, чтобы не падал")
    @Test
    
    @DisplayName("[/v2/car-registration/invoices/status][POST] Регистрация авто. Получить статус платежей по инвойсам")
    void getInvoiceStatus() {
        step("Запрос на получение статусов по инвойсу", () -> {
            var params = CarRegInvoiceStatusPostRequest.Params.builder()
                    .invoiceIds(List.of(1499))
                    .build();
            CarRegInvoicePostResponse carRegInvoiceStatusResp = carRegistrationService.getInvoiceStatus(userToken, params);
            step("Проверка ответа", () -> {
                soft.assertThat(carRegInvoiceStatusResp.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(carRegInvoiceStatusResp.getError()).as("Пришла ошибка").isEqualTo(false);
            });

        });
    }
}
