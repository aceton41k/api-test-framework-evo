package com.example.tests.evo.my_home;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.myhome.MyHomeListResponse;
import com.example.api.evo.models.myhome.MyHomeSaveRequest;
import com.example.api.evo.models.myhome.MyHomeSaveResponse;
import com.example.api.evo.services.myhome.MyHomeService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;


public class AddMyHomeTests extends EvoBaseTest {
    @Autowired
    MyHomeService myHomeService;

    Long homeId;
    String randomHomeName = "Дом " + RandomStringUtils.randomAlphabetic(5);

    @Test
    
    @DisplayName("[myhome.save] Создание нового дома")
    void saveNewHomeTest() {
        step("Запрос на создания дома", () -> {
            var params = MyHomeSaveRequest.Params.builder()
                    .myhomeId(0L)
                    .name(randomHomeName)
                    .build();
            MyHomeSaveResponse response = myHomeService.saveHomeById(params, headers);

            step("Проверка, что ответ содержит ид дома", () -> {
                soft.assertThat(response.getResult().getMyhomeId()).isGreaterThan(0);
                homeId = response.getResult().getMyhomeId();
            });
            step("Проверка списка домов", () -> {
                MyHomeListResponse listResponse = myHomeService.getMyHomeList(headers);
                MyHomeListResponse.Result actualHome = listResponse.getResult().stream()
                        .filter(home -> home.getId().equals(homeId))
                        .findFirst().orElseThrow(() -> new AssertionError("Дом с ID " + homeId + " не найден"));

                soft.assertThat(actualHome.getName())
                        .as("Проверка имени дома с ID " + homeId)
                        .isEqualTo(randomHomeName);
            });

        });
    }
}
