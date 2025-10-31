package com.example.tests.gosuslugi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.gosuslugi.models.login.GovApiLoginRequest;
import com.example.api.gosuslugi.models.login.GovApiLoginResponse;
import com.example.api.gosuslugi.services.GovApiAuthService;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GovApiLoginTests extends GovApiBaseTest {
    @Autowired
    GovApiAuthService govApiAuthService;

    @BeforeAll
    void setUp() {
        webSession = operations.login(userData.getUser("XAS")).getWebSession();
    }

    @Test
    
    @DisplayName("[/auth/login][POST] Авторизация")
    void getAutopayListTest() {
        step("Запрос на авторизацию", () -> {
            var params = GovApiLoginRequest.builder()
                    .webSession(webSession)
                    .build();
            GovApiLoginResponse response = govApiAuthService.login(params, webSession);

            step("Проверка ответа что данные совпадают", () -> {
                assertThat(response.getAccessToken()).isNotEmpty();
                assertThat(response.getTokenType()).isEqualTo("bearer");
                assertThat(response.getExpiresIn()).isEqualTo(EXPIRES_IN);
                assertThat(response.getUser().getId()).isEqualTo(XAS_ID);
                assertThat(response.getUser().getClientId()).isEqualTo(XAS_CLIENT_ID);
            });
        });
    }
}
