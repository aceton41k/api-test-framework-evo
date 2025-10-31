package com.example.tests.integration_client_ms;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.integration_client_ms.services.IntegrationClientMsApiDataBaseService;
import com.example.api.integration_client_ms.services.QuarantineService;

import java.util.Map;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

@Slf4j
public class QuarantineTests extends IntegrationClientMsApiBaseTest {
    @Autowired
    QuarantineService quarantineService;

    @Autowired
    IntegrationClientMsApiDataBaseService integrationClientMsApiDataBaseService;


    private static Stream<Arguments> cardStatus() {
        return Stream.of(Arguments.of(DROP_CARD, "-80"), Arguments.of(QUARANTINE_CARD, "-78"),
                Arguments.of(POSSIBLE_DROP_CARD, "-81"));
    }

    @ParameterizedTest
    @MethodSource("cardStatus")
    
    @DisplayName("[/v1/quarantine/block] [PUT] Блокировка и разблокировка карты")
    void changeStatusOfCard(String statusName, String statusCode) {
        step("Отправить запрос на изменения статуса карты", () -> {
            Map<String, String> request = Map.of("clientId", XASANS_CLIENT_ID, "cardHash", XASANS_WALLET_TOKEN,
                    "quarantineCardType", statusName);
            var response = quarantineService.blockCard(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getStatusCode()).isEqualTo(200);
            });
            step("Проверка что в бд поменялся статус", () -> {
                String dbStatus = integrationClientMsApiDataBaseService.waitUntilCardStatusEquals(XASANS_WALLET_TOKEN,
                        statusCode);

                soft.assertThat(dbStatus).isEqualTo(statusCode);
            });
        });

        step("Отправить запрос на разблокировку карты", () -> {
            Map<String, String> request = Map.of("clientId", XASANS_CLIENT_ID, "cardHash", XASANS_WALLET_TOKEN);
            var response = quarantineService.unblockCard(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getStatusCode()).isEqualTo(200);
            });
            step("Проверка что в бд поменялся статус на 1", () -> {
                String dbStatus = integrationClientMsApiDataBaseService.waitUntilCardStatusEquals(XASANS_WALLET_TOKEN,
                        "1");

                soft.assertThat(dbStatus).isEqualTo("1");
            });
        });
    }

}
