package com.example.tests.evo.settings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.settings.SettingsChangePinRequest;
import com.example.api.evo.services.settings.SettingsService;
import com.example.tests.evo.EvoBaseTest;
import com.example.utils.TokenGenerator;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.UserData.DEFAULT_PIN;


public class ChangePinTests extends EvoBaseTest {
    @Autowired
    SettingsService settingsService;

    String md5CurrentPin;
    String md5NewPin;

    @BeforeEach
    void setUp() {
        md5CurrentPin = TokenGenerator.encodePin(DEFAULT_PIN);
        md5NewPin = TokenGenerator.encodePin("35741");
    }

    @Test
    
    @DisplayName("[settings.change.pin] Сменить пин")
    void changePin() {
        step("Запрос на смену пина", () -> {
            var params = SettingsChangePinRequest.Params.builder()
                    .currentPin(md5CurrentPin)
                    .newPin(md5NewPin)
                    .build();
            ResponseWithOkResult response = settingsService.changePin(params, headers);

            step("Проверка, что ответ содержит ок ответ", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
    }
}
