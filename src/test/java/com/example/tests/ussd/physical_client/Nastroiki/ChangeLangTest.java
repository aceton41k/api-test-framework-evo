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
import static com.example.api.ussd.constants.TextMessageConfig.MARKETING_MSG_RU;
import static com.example.api.ussd.constants.TextMessageConfig.MARKETING_MSG_UZ;
import static com.example.api.ussd.constants.TextMessageConfig.NASTROIKI_CHANGELANG_END_RU;
import static com.example.api.ussd.constants.TextMessageConfig.NASTROIKI_CHANGELANG_END_UZ;
import static com.example.api.ussd.constants.TextMessageConfig.NASTROIKI_CHANGELANG_RU;
import static com.example.api.ussd.constants.TextMessageConfig.NASTROIKI_CHANGELANG_UZ;
import static com.example.api.ussd.constants.TextMessageConfig.NASTROIKI_RU;
import static com.example.api.ussd.constants.TextMessageConfig.NASTROIKI_UZ;
import static com.example.api.ussd.constants.TypeMessageConfig.FINAL_MESSAGE;
import static com.example.api.ussd.constants.TypeMessageConfig.MENU_MESSAGE;
import static com.example.api.ussd.constants.UserConfig.BASE_CLIENT;

public class ChangeLangTest extends UssdBaseTest {
    @Autowired
    UssdDBService dbService;
    @Autowired
    UssdMenuService ussd;

    MenuPostRequestResponse requestResponse;

    public Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(BASE_CLIENT, "RU", NASTROIKI_CHANGELANG_RU, "2", NASTROIKI_CHANGELANG_END_RU + MARKETING_MSG_RU, "RU", FINAL_MESSAGE),
                Arguments.of(BASE_CLIENT, "RU", NASTROIKI_CHANGELANG_RU, "1", NASTROIKI_CHANGELANG_END_UZ + MARKETING_MSG_RU, "UZ", FINAL_MESSAGE),
                Arguments.of(BASE_CLIENT, "RU", NASTROIKI_CHANGELANG_RU, "0", NASTROIKI_RU, "RU", MENU_MESSAGE),
                Arguments.of(BASE_CLIENT, "UZ", NASTROIKI_CHANGELANG_UZ, "2", NASTROIKI_CHANGELANG_END_RU + MARKETING_MSG_UZ, "RU", FINAL_MESSAGE),
                Arguments.of(BASE_CLIENT, "UZ", NASTROIKI_CHANGELANG_UZ, "1", NASTROIKI_CHANGELANG_END_UZ + MARKETING_MSG_UZ, "UZ", FINAL_MESSAGE),
                Arguments.of(BASE_CLIENT, "UZ", NASTROIKI_CHANGELANG_UZ, "0", NASTROIKI_UZ, "UZ", MENU_MESSAGE)
        );
    }

    
    @DisplayName("Раздел 'Настройки -> Смена языка'")
    @ParameterizedTest
    @MethodSource("cases")
    void changeLangTest(String phoneNum, String lang, String expectedMenu, String userChoice, String expectedFinalMenu, String langDB, int typeMsg){
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

        step("Переход в раздел 'Смены языка'", () -> {
            // указываем раздел 3 - Смена языка
            requestResponse.setBody("3");
            // переходим в раздел Смены языка
            requestResponse = ussd.baseResponse(requestResponse);
        });

        step("Проверка ответа", () -> {
            // проверка сообщения, которое будет выведено пользователю
            soft.assertThat(requestResponse.getBody()).isEqualTo(expectedMenu);
            // проверка типа сообщения: 2 - есть возможность ответа, 0 - финальное сообщение
            soft.assertThat(requestResponse.getOptionalParameters().get("515")).isEqualTo(2);
        });

        step("Смена языка", () -> {
            // указываем язык 1-UZ, 2-RU, 0-возвращение в предыдущий раздел (Настройки)
            requestResponse.setBody(userChoice);
            // выполняем смену языка
            requestResponse = ussd.baseResponse(requestResponse);

        });

        step("Проверка ответа", () -> {
            // проверка сообщения, которое будет выведено пользователю, после смены языка
            // либо сообщение при возвращении в предыдущий раздел
            soft.assertThat(requestResponse.getBody()).isEqualTo(expectedFinalMenu);
            // проверка типа сообщения: 2 - есть возможность ответа, 0 - финальное сообщение
            soft.assertThat(requestResponse.getOptionalParameters().get("515")).isEqualTo(typeMsg);
            // проверка смены языка в БД
            soft.assertThat(dbService.checkLanguage(phoneNum)).isEqualTo(langDB);
        });
    }
}
