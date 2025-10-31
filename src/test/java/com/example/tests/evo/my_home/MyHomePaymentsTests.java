package com.example.tests.evo.my_home;

import com.example.api.evo.models.myhome.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uz.click.evo_api.models.myhome.*;
import com.example.api.evo.services.myhome.MyHomePaymentsService;
import com.example.api.evo.services.myhome.MyHomeService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static com.example.api.evo.constants.ServiceData.SERVICE_ID_BEE;


public class MyHomePaymentsTests extends EvoBaseTest {
    @Autowired
    MyHomeService myHomeService;

    @Autowired
    MyHomePaymentsService myHomePaymentsService;

    Long homeId;
    Long favoritePaymentId;

    @BeforeEach
    void setUp() {
        step("Создать дом перед тестом", () -> {
            var params = MyHomeSaveRequest.Params.builder()
                    .myhomeId(0L)
                    .name("Test Home")
                    .build();
            homeId = myHomeService.saveHomeById(params, headers).getResult().getMyhomeId();
        });

        step("Добавить платеж в дом перед тестом", () -> {
            var parameters = MyHomePaymentsSaveRequest.Parameters.builder()
                    .amount("1000.0")
                    .phoneNum(phoneNumber)
                    .build();

            var params = MyHomePaymentsSaveRequest.Params.builder()
                    .serviceId(SERVICE_ID_BEE)
                    .myHomeId(homeId)
                    .parameters(parameters)
                    .build();

            myHomePaymentsService.addPaymentToMyHome(params, headers);
        });
        step("Получение ид платежа для тестов", () -> {
            var params =
                    MyHomePaymentsListRequest.Params.builder().myhomeId(homeId).build();
            favoritePaymentId = myHomePaymentsService
                    .getMyHomePaymentList(params, headers)
                    .getResult()
                    .getFirst()
                    .getId();
        });
    }

    @Test
    
    @DisplayName("[myhome.list] Получение списка домов")
    void getMyHomeListTest() {
        step("Запрос на получение списка домов", () -> {
            MyHomeListResponse response = myHomeService.getMyHomeList(headers);

            step("Проверка, что id не null", () -> {
                soft.assertThat(response.getResult().getFirst().getId()).isNotEqualTo(0);
                soft.assertThat(response.getResult().getFirst().getName()).isNotBlank();
            });
        });
    }

    @Test
    
    @DisplayName("[myhome.payments.list] Получение список платежей в доме")
    void getPaymentsListForHomeTest() {
        step("Запрос на получение списка платежей", () -> {
            MyHomePaymentsListRequest.Params params =
                    MyHomePaymentsListRequest.Params.builder().myhomeId(homeId).build();
            MyHomePaymentsListResponse response = myHomePaymentsService.getMyHomePaymentList(params,
                    headers);

            step("Проверка, что ответ содержит платеж", () -> {
                soft.assertThat(response.getResult().getFirst().getId()).isNotNull();
                soft.assertThat(response.getResult().getFirst().getId()).isNotEqualTo(0);
            });
        });
    }

    @Test
    
    @DisplayName("[myhome.payments.data] Получение поля для формы платежа мой дом")
    void getPaymentDataMyHomeTest() {
        step("Запрос на получение полей платежа", () -> {
            MyHomePaymentDataRequest.Params params = MyHomePaymentDataRequest.Params.builder()
                    .myHomeId(homeId)
                    .id(favoritePaymentId)
                    .build();
            MyHomePaymentDataResponse response = myHomePaymentsService.getMyHomePaymentData(params,
                    headers);

            step("Проверка, что ответ содержит шаги и не равен null", () ->
                    soft.assertThat(response.getResult().getStep()).isNotNull());
        });
    }

    @Test
    
    @DisplayName("[myhome.payments.info] Получение информацию платежа мой дом")
    void getPaymentInfoForMyHomeTest() {
        step("Запрос на получение информации о платеже", () -> {
            MyHomePaymentDataRequest.Params params = MyHomePaymentDataRequest.Params.builder()
                    .myHomeId(homeId)
                    .id(favoritePaymentId)
                    .build();
            MyHomePaymentInfoResponse response = myHomePaymentsService.getMyHomePaymentInfo(params,
                    headers);

            step("Проверка, что ответ содержит информацию и не равен null", () ->
                    soft.assertThat(response.getResult().getInfo()).isNotNull());
        });
    }

    @Test
    
    @DisplayName("[myhome.get.popular] Получение список популярных сервисов мой дом")
    void getPopularServicesMyHomeTest() {
        step("Запрос на получение популярных платежей", () -> {
            MyHomeGetPopularResponse response = myHomeService.getMyHomePopular(headers);

            step("Проверка, что ответ содержит сервис ид", () -> {
                soft.assertThat(response.getResult().getFirst().getId()).isNotNull();
                soft.assertThat(response.getResult().size()).isGreaterThanOrEqualTo(5);
            });
        });
    }
}
