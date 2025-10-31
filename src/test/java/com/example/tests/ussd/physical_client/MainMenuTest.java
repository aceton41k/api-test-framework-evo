package com.example.tests.ussd.physical_client;

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
import static com.example.api.ussd.constants.UserConfig.BASE_CLIENT;


public class MainMenuTest extends UssdBaseTest {
    @Autowired
    UssdDBService dbService;
    @Autowired
    UssdMenuService ussd;

    public Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(BASE_CLIENT, "RU", MAIN_MENU_RU + MARKETING_MSG_RU),
                Arguments.of(BASE_CLIENT, "UZ", MAIN_MENU_UZ + MARKETING_MSG_UZ)
        );
    }

    
    @DisplayName("Получение главного меню физического лица")
    @ParameterizedTest
    @MethodSource("cases")
    void getMainMenuTest(String phoneNum, String lang, String expectedMenu) {
        step("Подготовка пользователя в БД", () -> {
            dbService.changeLangClient(phoneNum, lang);
        });
        step("Запрос на получение главного меню", () -> {
            var startBody = MenuPostRequestResponse.builder()
                    .headers(Map.of())
                    .body("")
                    .optionalParameters(Map.of())
                    .phoneNumber(phoneNum)
                    .phoneNumberStr(phoneNum)
                    .build();

            MenuPostRequestResponse mainMenuBody = ussd.baseResponse(startBody);

            step("Проверка ответа", () -> {
                // проверка сообщения, которое будет выведено пользователю
                soft.assertThat(mainMenuBody.getBody()).isEqualTo(expectedMenu);
                // проверка типа сообщения: 2 - есть возможность ответа, 0 - финальное сообщение
                soft.assertThat(mainMenuBody.getOptionalParameters().get("515")).isEqualTo(2);
            });
        });
    }
}

