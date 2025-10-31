package com.example.tests.evo.my_home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.myhome.MyHomeDeleteRequest;
import com.example.api.evo.models.myhome.MyHomeListResponse;
import com.example.api.evo.models.myhome.MyHomeSaveRequest;
import com.example.api.evo.services.myhome.MyHomeService;
import com.example.tests.evo.EvoBaseTest;

import java.util.Optional;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DeleteMyHomeTests extends EvoBaseTest {
    @Autowired
    MyHomeService myHomeService;

    Long homeId;

    @BeforeEach
    void setUp() {
        step("Создать дом перед тестом", () -> {
            var params = MyHomeSaveRequest.Params.builder()
                    .myhomeId(0L)
                    .name("Test Home")
                    .build();
            homeId = myHomeService.saveHomeById(params, headers).getResult().getMyhomeId();
            assertThat(homeId).isNotEqualTo(0);
        });
    }

    @Test
    
    @DisplayName("[myhome.delete] Удаление дома")
    void deleteHomeTest() {
        step("Запрос на удаление дома по ид", () -> {
            MyHomeDeleteRequest.Params params =
                    MyHomeDeleteRequest.Params.builder().myhomeId(homeId).build();
            ResponseWithOkResult response = myHomeService.deleteHomeById(params, headers);

            step("Проверка, что ответ содержит ок ответ", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
        step("Проверить что дом пропал из списка", () -> {
            Optional<MyHomeListResponse.Result> response = myHomeService.getHomeById(headers, homeId);

            step("Проверка, что ответ пустой", () -> soft.assertThat(response).isEmpty());
        });
    }
}
