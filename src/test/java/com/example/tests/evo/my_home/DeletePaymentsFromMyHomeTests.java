package com.example.tests.evo.my_home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.myhome.MyHomePaymentsDeleteRequest;
import com.example.api.evo.models.myhome.MyHomePaymentsListRequest;
import com.example.api.evo.models.myhome.MyHomePaymentsSaveRequest;
import com.example.api.evo.models.myhome.MyHomeSaveRequest;
import com.example.api.evo.services.myhome.MyHomePaymentsService;
import com.example.api.evo.services.myhome.MyHomeService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.ServiceData.API_VERSION;
import static com.example.api.evo.constants.ServiceData.SERVICE_ID_BEE;


public class DeletePaymentsFromMyHomeTests extends EvoBaseTest {
    @Autowired
    MyHomeService myHomeService;

    @Autowired
    MyHomePaymentsService myHomePaymentsService;

    Long homeId, paymentId;

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
        step("Получить ид платежа для тестов", () -> {
            var params = MyHomePaymentsListRequest.Params.builder()
                    .myhomeId(homeId)
                    .apiVersion(API_VERSION)
                    .build();
            paymentId = myHomePaymentsService
                    .getMyHomePaymentList(params, headers)
                    .getResult()
                    .getFirst()
                    .getId();
        });
    }

    @Test
    
    @DisplayName("[myhome.payments.delete] Удаление платеж из дома")
    void deletePaymentFromMyHomeTest() {
        step("Запрос на удаление платежа из дома", () -> {
            MyHomePaymentsDeleteRequest.Params params = MyHomePaymentsDeleteRequest.Params.builder()
                    .myhomeId(homeId)
                    .id(paymentId)
                    .build();
            ResponseWithOkResult response = myHomePaymentsService.deletePaymentFromHome(params,
                    headers);

            step("Проверка, что ответ содержит ок ответ", () ->
                    assertThat(response.getResult()).isEqualTo("ok"));
        });
        step("Получение список платежей и проверить что там пусто", () -> {
            var params = MyHomePaymentsListRequest.Params.builder().myhomeId(homeId).build();

            soft.assertThat(myHomePaymentsService
                            .getMyHomePaymentList(params, headers)
                            .getResult()
                            .size())
                    .isEqualTo(0);
        });
    }
}
