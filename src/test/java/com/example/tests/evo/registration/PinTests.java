package com.example.tests.evo.registration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.registration.PinResetRequest;
import com.example.api.evo.models.registration.PinResetResponse;
import com.example.api.evo.services.registration.AdditionalRegistrationService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.UserData.DEFAULT_SMS;


public class PinTests extends EvoBaseTest {
    @Autowired
    AdditionalRegistrationService additionalRegistrationService;

    @Test
    
    @DisplayName("[pin.reset.request.new] Восстановление пина новый метод")
    void pinResetRequestNewTest() {
        step("Запрос на сброс пина", () -> {
            PinResetResponse response = additionalRegistrationService.pinResetRequest(headers);

            step("Проверка что при ответе получили deviceId", () -> {
                soft.assertThat(response.getResult().getConfirmNeeded()).isTrue();
                soft.assertThat(response.getResult().getRegisterToken()).isNull();
                soft.assertThat(response.getResult().getSms()).isFalse();
            });
        });
    }

    @Test
    
    @DisplayName("[pin.reset] Восстановления пина")
    void pinReset() {
        step("Запрос на сброс пина]", () -> {
            additionalRegistrationService.pinResetRequest(headers);
        });
        step("Сброс пина]", () -> {
            var params =
                    PinResetRequest.Params.builder().smsCode(DEFAULT_SMS).build();
            PinResetResponse response = additionalRegistrationService.pinReset(params, headers);

            step("Проверка, что при ответе получили registerToken", () -> {
                assertThat(response.getResult().getRegisterToken()).isNotEmpty();
            });
        });
    }
}
