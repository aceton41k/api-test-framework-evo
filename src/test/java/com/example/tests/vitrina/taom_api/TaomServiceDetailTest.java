package com.example.tests.vitrina.taom_api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.vitrina.taom_api.constants.TaomConstants;
import com.example.api.vitrina.taom_api.services.TaomServiceDetailService;
import com.example.tests.vitrina.VitrinaBaseTest;

import static io.qameta.allure.Allure.step;

public class TaomServiceDetailTest extends VitrinaBaseTest {
    @Autowired
    TaomServiceDetailService taomServiceDetailService;

    @ParameterizedTest
    @ValueSource(strings = {TaomConstants.SINGLE_IKPU_SERVICE_ID, TaomConstants.MULTIPLE_IKPU_SERVICE_ID})

    @DisplayName("[/services/detail/:merchant_id/:service_id][GET] Успешное получение деталей заведения")
    void testGetServiceDetails(String serviceId) {
        step("GET /services/detail/:merchant_id/:service_id", () -> {
            Response response = taomServiceDetailService.getServiceDetails(serviceId);
            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/service.schema.json");
                });
            });
        });
    }
}
