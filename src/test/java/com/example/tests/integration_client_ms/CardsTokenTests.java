package com.example.tests.integration_client_ms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.integration_client_ms.models.CardTokenErrorResponse;
import com.example.api.integration_client_ms.models.CardTokenResponse;
import com.example.api.integration_client_ms.services.GetTokenService;

import java.util.Map;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

public class CardsTokenTests extends IntegrationClientMsApiBaseTest {

    @Autowired
    GetTokenService getTokenService;

    private static Stream<Arguments> invalidParamsForCardToken() {
        return Stream.of(Arguments.of("123", "389648"),
                Arguments.of("D77E66A6AABE88ECFF0C02E4EEB53677FD06C1E64FB864434A85AF947A50F759", "123"),
                Arguments.of("test", "1234"));
    }

    @Test
    
    @DisplayName("[v1/cards/token] [GET] Получение токена")
    void getCardToken() {
        Map<String, String > request = Map.of("cardHash",
                "D77E66A6AABE88ECFF0C02E4EEB53677FD06C1E64FB864434A85AF947A50F759", "clientId", "389648");
        step("Отправить запрос на получение цены", () -> {
            var response = getTokenService.getToken(request, CardTokenResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getCardToken()).as("cardToken").isEqualTo("5af445deca57d70e28fc8b5a");
            });
        });
    }

    @ParameterizedTest
    @MethodSource("invalidParamsForCardToken")
    
    @DisplayName("[v1/cards/token] [GET] Получение токена с не валидными данными")
    void getCardTokenByInvalidParams(String cardHash, String clientId) {
        Map<String, String > request = Map.of("cardHash", cardHash, "clientId", clientId);
        step("Отправить запрос на получение цены", () -> {
            var response = getTokenService.getToken(request, CardTokenErrorResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getCode()).as("code").isEqualTo("card_not_found");
                soft.assertThat(response.getMessage()).as("message").isEqualTo("Карта не найден Карта не найден. ");
                soft.assertThat(response.getTarget()).as("target").isEqualTo("card_not_found");
                soft.assertThat(response.getLocale().getMessage()).as("locale message").isEqualTo("Карта не найден ");
                soft.assertThat(response.getLocale().getExtra()).as("locale extra").isEqualTo("Карта не найден");
            });
        });
    }

    @ParameterizedTest
    @CsvSource("D77E66A6AABE88ECFF0C02E4EEB53677FD06C1E64FB864434A85AF947A50F759, text")
    
    @DisplayName("[v1/cards/token] [GET] Получение токена когда clientId не число)")
    void getCardTokenWhenClientIdIsString(String cardHash, String clientId) {
        Map<String, String> request = Map.of("cardHash", cardHash, "clientId", clientId);
        step("Отправить запрос на получение цены", () -> {
            var response = getTokenService.getToken(request, CardTokenErrorResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getCode()).as("code").isEqualTo("input_validation_error");
                soft.assertThat(response.getMessage()).as("message").isEqualTo("Parameter type mismatch");
                soft.assertThat(response.getTarget()).as("target").isEqualTo("clientId");
                soft.assertThat(response.getLocale().getMessage()).as("locale message")
                    .isEqualTo("Некорректный тип параметра");
                soft.assertThat(response.getLocale().getExtra()).as("locale extra")
                    .isEqualTo("Параметер 'clientId' имеет некорректный тип");
            });
        });
    }
}
