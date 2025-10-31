package com.example.tests.vitrina.vitrina_api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.vitrina.vitrina_api.constants.VitrinaConstants;
import com.example.api.vitrina.vitrina_api.models.VitrinaServiceResponse;
import com.example.api.vitrina.vitrina_api.models.auth.VitrinaAuthResponse;
import com.example.api.vitrina.vitrina_api.services.PlatformService;
import com.example.api.vitrina.vitrina_api.services.VitrinaAuthService;
import com.example.api.vitrina.vitrina_api.services.VitrinaDevToolsService;
import com.example.api.vitrina.vitrina_api.services.VitrinaUpdatePlatformService;
import com.example.tests.vitrina.VitrinaBaseTest;

import static io.qameta.allure.Allure.step;

public class VitrinaUpdatePlatformTest extends VitrinaBaseTest {

    @Autowired
    VitrinaUpdatePlatformService vitrinaUpdatePlatformService;
    @Autowired
    VitrinaAuthService vitrinaAuthService;
    @Autowired
    PlatformService platformService;
    @Autowired
    VitrinaDevToolsService vitrinaDevToolsService;

    String vitrinaToken;
    String platformAppId;

    @BeforeAll
    void setUp() {
        VitrinaAuthResponse response = vitrinaAuthService.authDevUser();
        Assertions.assertNotNull(response.getToken(), "Не удалось получить токен авторизации в preconditions");
        vitrinaToken = response.getToken();
    }

    @Test
    
    @DisplayName("[/services/:service_id/update-platform] [POST] Отправка витрины на модерацию")
        void shouldSuccessfullySendServiceToModeration() {
        step("POST /services/:service_id/update-platform", () -> {
            Response response = vitrinaUpdatePlatformService.updatePlatform(vitrinaToken);

            step("Expected Result", () -> {
                step("Статус код = 201", () -> {
                    soft.assertThat(response.getStatusCode())
                            .as("Статус код должен быть 201 Created")
                            .isEqualTo(HttpStatus.SC_CREATED);
                });

                VitrinaServiceResponse serviceResponse = response.as(VitrinaServiceResponse.class);

                step("Поле id совпадает с 68a2cbd07335152f561afb4a", () -> {
                    soft.assertThat(serviceResponse.getId())
                            .as("ID сервиса должен совпадать с ожидаемым")
                            .isEqualTo(VitrinaConstants.SERVICE_ID_DEV_USER);
                });
                step("Значение поля platform_app_id не пустое", () -> {
                    soft.assertThat(serviceResponse.getPlatformAppId())
                            .as("platform_app_id не должен быть пустым")
                            .isNotNull()
                            .isNotEmpty();
                });
                step("Значение поля platform_sent = true", () -> {
                    soft.assertThat(serviceResponse.getPlatformSent())
                            .as("platform_sent должен быть true после отправки")
                            .isTrue();
                });

                platformAppId = serviceResponse.getPlatformAppId();
            });
        });

        step("GET api/miniapps/:platform_app_id", () -> {
            Response platformResponse = platformService.getMiniApp(platformAppId);

            step("Expected Result", () -> {
                step("Статус код = 200", () -> {
                    soft.assertThat(platformResponse.getStatusCode())
                            .as("GET запрос к платформе должен вернуть 200")
                            .isEqualTo(HttpStatus.SC_OK);
                });

                String status = platformResponse.jsonPath().getString("status");
                step("status = STEP_2", () -> {
                    soft.assertThat(status)
                            .as("Статус заявки на платформе должен быть STEP_2")
                            .isEqualTo("STEP_2");
                });
            });
        });
    }

    @AfterAll
    void cleanUp() {
        platformService.deleteMiniApp(platformAppId);

        vitrinaDevToolsService.resetVitrina(vitrinaToken);
    }
}
