package com.example.tests.evo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.debt.GetDebtListResponse;
import com.example.api.evo.services.debt.DebtService;

import static io.qameta.allure.Allure.step;

public class DebtTests extends EvoBaseTest {
    @Autowired
    DebtService debtService;

    @Test
    
    @DisplayName("debt.list - Получение списка должников")
    void getDebtListTest() {
        step("Выполнение запроса debt.list", () -> {
            GetDebtListResponse response = debtService.getDebtList(headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).isNotNull().allSatisfy(r -> {

                    soft.assertThat(r.getDebtId())
                            .as("ID долга должен быть указан")
                            .isNotNull()
                            .isNotEqualTo(0);
                    if (r.getImageUrl() != null && r.getImageUrl().startsWith("http")) {
                        soft.assertThatUrl(r.getImageUrl()).isReachable();
                    }

                    soft.assertThat(r.getPhoneNumber())
                            .as("Номер телефона не должен быть пустым")
                            .isNotBlank();

                    soft.assertThat(r.getName())
                            .as("Имя должно быть указано")
                            .isNotBlank();

                    soft.assertThat(r.getAmount())
                            .as("Сумма долга должна быть больше нуля")
                            .isGreaterThan(0);

                    soft.assertThat(r.getType())
                            .as("Тип долга должен быть указан")
                            .isNotBlank();

                    soft.assertThat(r.getIsOwnDebt())
                            .as("Флаг принадлежности долга должен быть задан?")
                            .isNotNull();
                });
            });
        });
    }
}