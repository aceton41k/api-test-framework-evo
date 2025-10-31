package com.example.tests.ussd.physical_client.Nastroiki;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.tests.ussd.UssdBaseTest;
import com.example.api.ussd.models.menu.MenuPostRequestResponse;
import com.example.api.ussd.services.UssdDBService;
import com.example.api.ussd.services.UssdMenuService;

import java.util.Map;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static com.example.api.ussd.constants.TextMessageConfig.NASTROIKI_RU;
import static com.example.api.ussd.constants.TextMessageConfig.NASTROIKI_UZ;
import static com.example.api.ussd.constants.UserConfig.BASE_CLIENT;

public class NastroikiMenuTest extends UssdBaseTest {
    @Autowired
    UssdDBService dbService;
    @Autowired
    UssdMenuService ussd;

    MenuPostRequestResponse requestResponse;

    public Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(BASE_CLIENT, "RU", NASTROIKI_RU),
                Arguments.of(BASE_CLIENT, "UZ", NASTROIKI_UZ)
        );
    }

    
    @DisplayName("Раздел 'Настройки'")
    @ParameterizedTest
    @MethodSource("cases")
    void getNastroikiTest(String phoneNum, String lang, String expectedMenu) {
        step("Подготовка пользователя в БД", () -> {
            dbService.changeLangClient(phoneNum, lang);
        });

        step("Открытие главного меню USSD", () -> {
            // формируем тело начального запроса
            requestResponse = MenuPostRequestResponse.builder()
                    .headers(Map.of())
                    .body("")
                    .optionalParameters(Map.of())
                    .phoneNumber(phoneNum)
                    .phoneNumberStr(phoneNum)
                    .build();
            // получаем главное меню USSD
            requestResponse = ussd.baseResponse(requestResponse);
        });

        step("Переход в раздел 'Настройки'", () -> {
            // указываем раздел 5 - Настройки в запросе
            requestResponse.setBody("5");
            // переходим в раздел Настройки
            requestResponse = ussd.baseResponse(requestResponse);
        });

        step("Проверка ответа", () -> {
            // проверка сообщения, которое будет выведено пользователю
            soft.assertThat(requestResponse.getBody()).isEqualTo(expectedMenu);
            // проверка типа сообщения: 2 - есть возможность ответа, 0 - финальное сообщение
            soft.assertThat(requestResponse.getOptionalParameters().get("515")).isEqualTo(2);
        });
    }
}
