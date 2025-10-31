package com.example.tests.evo.settings;

import com.example.api.evo.models.settings.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.AccountData;
import com.example.api.evo.models.ResponseWithOkResult;
import uz.click.evo_api.models.settings.*;
import com.example.api.evo.services.settings.DeviceListService;
import com.example.api.evo.services.settings.SettingsService;
import com.example.tests.evo.EvoBaseTest;
import com.example.utils.DateTimeUtil;

import java.io.File;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SettingsTests extends EvoBaseTest {
    @Autowired
    SettingsService settingsService;

    @Autowired
    DeviceListService deviceListService;

    @Test
    
    @DisplayName("[get.user.profile] Получить информацию о пользователе")
    @Disabled("в этом методе баг есть")
    void getAutopayListTest() {
        step("Запрос на получение информации о пользователе", () -> {
            GetUserProfileResponse response = settingsService.getUserProfile(headers);

            step("Проверка, что ответ содержит имя и клиент ид", () -> {
                assertThat(response.getResult().getName()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[settings.photo.upload] Добавить фото пользователя")
    @Disabled("фото не добавляется, баг, добавить ссылку")
    void uploadPhoto() {
        File file = new File("src/test/resources/image.jpg");
        SettingsUploadPhotoResponse response =
                step("Добавления фото", () -> settingsService.uploadPhoto(headers, file));
        step(
                "Проверка, что ответ содержит url для фото",
                () -> assertThat(response.getResult().getImageUrl()).isNotNull());
    }

    @Test
    
    @DisplayName("[settings.photo.remove] Удалить фото пользователя")
    @Disabled("нужно добавить прекондишен добавление фото")
    void removePhoto() {
        step("Удаление фото", () -> {
            ResponseWithOkResult response = settingsService.removeUserPhoto(headers);

            step("Проверка, что ответ содержит ок ответ", () -> {
                assertThat(response.getResult()).isEqualTo("ok");
            });
        });
    }

    @Test
    
    @DisplayName("[settings.change.default.account] Изменить основную карту")
    @Disabled("для этого теста нужна карта")
    void changeDefaultAccount() {
        step("Изменить основную карту для оплат", () -> {
            var params = SettingsChangeDefaultAccountRequest.Params.builder()
                    .accountId(AccountData.XAS_ACCOUNT_ID_1)
                    .build();
            ResponseWithOkResult response = settingsService.changeDefaultAccount(params,
                    headers);

            step("Проверка, что ответ содержит ок ответ", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
    }

    @Test
    
    @DisplayName("[settings.change.profile] Сменить профиль")
    @Disabled("что то со статусом проблемы потом переделаю")
    void changeProfile() {
        String name = DateTimeUtil.getCurrentTimestamp();
        step("Запрос на смену профиля", () -> {
            var params = SettingsChangeProfileRequest.Params.builder()
                    .name("Xasan" + name)
                    .surname("KARIMJONOV")
                    .patronym("AVAZJON O'G'LI")
                    .birthdate(AccountData.XAS_BIRTH_DATE)
                    .regionCode("03")
                    .build();
            SettingsChangeProfileResponse response = settingsService.changeProfile(params,
                    headers);

            step(
                    "Проверка, что ответ содержит измененное имя",
                    () -> assertThat(response.getResult().getName()).isEqualTo("Xasan" + name));
        });
    }

    @Test
    
    @DisplayName("[change.language] Сменить язык")
    void changeLanguage() {
        step("Запрос на смену языка", () -> {
            var params = ChangeLanguageRequest.Params.builder().language("ru").build();
            ResponseWithOkResult response = settingsService.changeLanguage(params, headers);

            step("Проверка, что ответ содержит ок ответ", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
    }

    @Test
    
    @DisplayName("[device.list] Получить список девайсов")
    void getDeviceList() {
        var response = step("Запрос на получение списка девайсов",
                () -> deviceListService.getDeviceList(headers));
        step("Проверка, что ответ содержит девайсы",
                () -> assertThat(response.getResult().getFirst().getDeviceId()).isNotNull());
    }
}
