package com.example.tests.evo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.gamification.UserProgressPrivilegesGetResponse;
import com.example.api.evo.models.gamification.UserProgressStatusGetResponse;
import com.example.api.evo.services.gamification.GamificationService;

import static io.qameta.allure.Allure.step;

public class GamificationTests extends EvoBaseTest {
    @Autowired
    GamificationService gamificationService;

    @Test
    
    @DisplayName("[user.progress.status.get] Получить статус прогресса пользователя")
        // TODO: Добавить проверки на разные статусы пользователей
    void getUserProgressStatusTest() {
        step("Запрос на получение статуса", () -> {
            UserProgressStatusGetResponse response = gamificationService.getUserStatusProgress(headers);

            step("Проверка, что список разрешённых карт не пустой", () -> {
                soft.assertThat(response.getResult().getLevel()).isIn(1, 2, 3, 4);
                soft.assertThat(response.getResult().getTopLevel()).isEqualTo(4);
                soft.assertThat(response.getResult().getTitle()).isIn("Знакомые", "Близкие", "Гость", "Друзья");
            });
        });
    }

    @Test
    
    @DisplayName("[user.progress.privileges.get] Получить привилегии прогресса пользователя")
    void getUserProgressPrivilegesTest() {
        step("Запрос на получение статуса", () -> {
            UserProgressPrivilegesGetResponse response = gamificationService.getUserPrivilegesProgress(
                    headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Проверка всех уровней")
                        .hasSize(4)
                        .allSatisfy(item -> {
                            soft.assertThat(item.getLevel()).isBetween(1, 4);
                            soft.assertThat(item.getTitle()).isNotBlank();
                            soft.assertThat(item.getDescription()).isNotBlank();
                            soft.assertThat(item.getPrivileges())
                                    .as("Привилегии не должны быть пустыми")
                                    .isNotEmpty()
                                    .allSatisfy(priv -> {
                                        soft.assertThat(priv.getText()).isNotBlank();
                                        soft.assertThatUrl(priv.getIcon()).isReachable();
                                    });
                            soft.assertThat(item.getAction()).isNotNull();
                            soft.assertThat(item.getAction().getText()).isNotBlank();
                            soft.assertThat(item.getAction().getDeeplink()).isNotNull();
                        });
            });
        });
    }
}
