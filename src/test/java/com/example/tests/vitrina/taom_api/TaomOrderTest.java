package com.example.tests.vitrina.taom_api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.vitrina.taom_api.constants.TaomConstants;
import com.example.api.vitrina.taom_api.services.TaomOrderService;
import com.example.tests.vitrina.VitrinaBaseTest;

import static io.qameta.allure.Allure.step;

public class TaomOrderTest extends VitrinaBaseTest {
    @Autowired
    TaomOrderService taomOrderService;

    String token;

    @BeforeAll
    public void getToken() {
        token = operations.login(userData.getUser("NIK")).getWebSession();
    }

    @ParameterizedTest
    @ValueSource(strings = {TaomConstants.SINGLE_IKPU_SERVICE_ID, TaomConstants.MULTIPLE_IKPU_SERVICE_ID})
    
    @DisplayName("[/:merchant_id/:service_id/orders/list] [GET] Получить список заказов")
    void testGetAllOrdersList(String serviceId) {
        step("GET /:merchant_id/:service_id/orders/list", () -> {
            Response response = taomOrderService.getOrders(serviceId, token);
            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/orders-list.schema.json");
                });
            });
        });
    }
}
