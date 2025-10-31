package com.example.tests.vitrina.taom_api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.vitrina.taom_api.constants.TaomConstants;
import com.example.api.vitrina.taom_api.services.TaomCategoryService;
import com.example.tests.vitrina.VitrinaBaseTest;

import static io.qameta.allure.Allure.step;

public class TaomCategoryTest extends VitrinaBaseTest {
    @Autowired
    TaomCategoryService taomCategoryService;

    @ParameterizedTest
    @ValueSource(strings = {TaomConstants.SINGLE_IKPU_SERVICE_ID, TaomConstants.MULTIPLE_IKPU_SERVICE_ID})
    
    @DisplayName("[/:merchantId/:shopId/categories][GET] Успешное получение не пустого списка категорий")
    void testGetAllCategoryList(String serviceId) {
        step("GET /:merchant_id/:service_id/categories", () -> {
            Response response = taomCategoryService.getAllCategories(serviceId);
            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/categories.schema.json");
                });
            });
        });
    }
}
