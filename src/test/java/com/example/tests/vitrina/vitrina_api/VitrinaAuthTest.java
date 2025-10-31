package com.example.tests.vitrina.vitrina_api;

import static io.qameta.allure.Allure.step;
import static com.example.api.vitrina.vitrina_api.constants.VitrinaConstants.DIVKIT_DEV_CLICK_MERCHANT_ID;
import static com.example.api.vitrina.vitrina_api.constants.VitrinaConstants.DIVKIT_DEV_CLICK_MERCHANT_USER_ID;
import static com.example.api.vitrina.vitrina_api.constants.VitrinaConstants.DIVKIT_DEV_MERCHANT_ID;
import static com.example.api.vitrina.vitrina_api.constants.VitrinaConstants.DIVKIT_DEV_MERCHANT_NAME;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.restassured.response.Response;

import com.example.api.vitrina.vitrina_api.models.VitrinaAuthResponse;
import com.example.api.vitrina.vitrina_api.models.auth.VitrinaAuthRequest;
import com.example.api.vitrina.vitrina_api.services.VitrinaAuthService;
import com.example.tests.vitrina.VitrinaBaseTest;


public class VitrinaAuthTest extends VitrinaBaseTest {
    @Autowired
    VitrinaAuthService authService;
    Map<String, String> headers;

    @BeforeEach
    void setUp() {
       headers = Map.of("Authorization", operations.login(userData.getUser("NIK")).getWebSession());
    }

    @Test
    
    @DisplayName("[/click-business/auth][POST] Получить информацию мерчанта")
    void authTest() {
        step("Отправить POST-запрос", () -> {
            Response res = authService.auth(VitrinaAuthRequest.builder().merchantId(DIVKIT_DEV_CLICK_MERCHANT_ID).build(), headers);

            step("Проверка что статус 201", () -> {
                soft.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
            });

            VitrinaAuthResponse authRes = res.as(VitrinaAuthResponse.class);

            step("Проверка что данные соответсвуют DivKit test", () -> {
                soft.assertThat(authRes.getId()).as("id не от DivKit Test").isEqualTo(DIVKIT_DEV_MERCHANT_ID);
                soft.assertThat(authRes.getName()).as("name не от DivKit Test").isEqualTo(DIVKIT_DEV_MERCHANT_NAME);
                soft.assertThat(authRes.getMerchantId()).as("merhachant_id не от DivKit Test")
                        .isEqualTo(DIVKIT_DEV_CLICK_MERCHANT_ID);
                soft.assertThat(authRes.getMerchantUserId()).as("merchant_user_id не от DivKit Test")
                        .isEqualTo(DIVKIT_DEV_CLICK_MERCHANT_USER_ID);
                soft.assertThat(authRes.getPhoneNumber()).as("phone_number не тестовый - ...2070")
                        .isEqualTo(userData.getUser("NIK").getPhoneNumber());
                soft.assertThat(authRes.getToken()).as("token не валидный JWT")
                        .matches("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$");
                soft.assertThatUrl(authRes.getLogo()).isReachable();
            });

        });
    }
}
