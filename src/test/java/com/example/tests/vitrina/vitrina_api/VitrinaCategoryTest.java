package com.example.tests.vitrina.vitrina_api;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.vitrina.vitrina_api.constants.VitrinaConstants;
import com.example.api.vitrina.vitrina_api.models.VitrinaCategoryResponse;
import com.example.api.vitrina.vitrina_api.models.auth.VitrinaAuthRequest;
import com.example.api.vitrina.vitrina_api.models.auth.VitrinaAuthResponse;
import com.example.api.vitrina.vitrina_api.services.VitrinaAuthService;
import com.example.api.vitrina.vitrina_api.services.VitrinaCategoryService;
import com.example.tests.vitrina.VitrinaBaseTest;

import java.util.List;
import java.util.Map;

import static io.qameta.allure.Allure.step;


public class VitrinaCategoryTest extends VitrinaBaseTest {
    @Autowired
    VitrinaCategoryService vitrinaCategoryService;
    @Autowired
    VitrinaAuthService vitrinaAuthService;

    Map<String, String> headers;
    String vitrinaToken;

    @BeforeAll
    void setUp() {
        headers = Map.of("Authorization", operations.login(userData.getUser("NIK")).getWebSession());
        VitrinaAuthResponse response = vitrinaAuthService.auth(VitrinaAuthRequest.builder().merchantId(VitrinaConstants.DIVKIT_DEV_CLICK_MERCHANT_ID).build(), headers).as(VitrinaAuthResponse.class);
        Assertions.assertNotNull(response.getToken(), "Не удалось получить токен авторизации в preconditions");
        vitrinaToken = response.getToken();
    }

    @Test
    
    @DisplayName("[/:merchantId/:shopId/categories][GET] Получить список категорий")
    void getServiceCategoryListTest() {
        step("Запрос на получение категорий", () -> {
            Response response = vitrinaCategoryService.getCategoryList(vitrinaToken);

            soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
            soft.assertThat(response).isNotNull();
            soft.assertThat(response).validateJsonSchema("json_schema/vitrina/categories.schema.json");

            List<VitrinaCategoryResponse> categoryResponse = response.as(new TypeRef<List<VitrinaCategoryResponse>>() {});

            step("Проверка ответа", () -> {
                soft.assertThat(categoryResponse).allSatisfy(r -> {
                    soft.assertThat(r.getMerchantId()).as("Merchant ID").isEqualTo(DIVKIT_DEV_MERCHANT_ID);
                    soft.assertThat(r.getServiceId()).as("Service ID").isEqualTo(SERVICE_ID_1);
                    soft.assertThat(r.getName()).as("Name").hasSizeGreaterThan(2);
                    soft.assertThat(r.getId()).as("ID").hasSize(24);
                    soft.assertThat(r.getStatus()).as("Status").isNotNull();
                });
            });
        });
    }
}
