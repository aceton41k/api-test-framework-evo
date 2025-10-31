package com.example.tests.evo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.BaseErrorResponse;
import com.example.api.evo.models.regular_payments.RegularPaymentsListResponse;
import com.example.api.evo.models.regular_payments.RegularPaymentsOfResponse;
import com.example.api.evo.models.regular_payments.RegularPaymentsOnResponse;
import com.example.api.evo.services.regular_payments.RegularPaymentsListService;
import com.example.jupiter.annotations.SkipAuthSetup;

import java.util.Collections;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static com.example.api.evo.constants.ErrorCodes.SESSION_BLOCKED;

public class RegularPaymentsTests extends EvoBaseTest {
    @Autowired
    RegularPaymentsListService regularPaymentsListService;

    Map<String, String> emptyHeader = Collections.emptyMap();

    @Test
    
    @DisplayName("[regular.payments.list] Получение списка регулярных платежей")
    void getRegularPaymentList() {
        step("[regular.payments.list] Получение списка регулярных платежей", () -> {
            RegularPaymentsListResponse response = regularPaymentsListService.getRegularPaymentsList(
                    RegularPaymentsListResponse.class,
                    headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Проверка всех элементов в списке result")
                        .allSatisfy(result -> {
                            soft.assertThat(result.getPaymentId())
                                    .as("payment_id не должен быть пустым")
                                    .isNotBlank();
                            soft.assertThat(result.getParameter())
                                    .as("parameter не должен быть пустым")
                                    .isNotBlank();
                            soft.assertThat(result.getAmount())
                                    .as("amount должен быть больше 0")
                                    .isNotNull()
                                    .isGreaterThan(0);
                            soft.assertThat(result.getDatetime())
                                    .as("datetime должен быть положительным числом")
                                    .isNotNull()
                                    .isGreaterThan(0L);
                            RegularPaymentsListResponse.Result.Service service = result.getService();
                            soft.assertThat(service)
                                    .as("service не должен быть null")
                                    .isNotNull();
                            soft.assertThat(service.getId())
                                    .as("service.id должен быть положительным")
                                    .isGreaterThan(0);
                            soft.assertThat(service.getName())
                                    .as("service.name не должен быть пустым")
                                    .isNotBlank();
                            soft.assertThat(service.getImage())
                                    .as("service.image должен быть валидным URL")
                                    .isNotBlank()
                                    .startsWith("https://");
                            soft.assertThat(service.getCategoryId())
                                    .as("service.category_id должен быть положительным")
                                    .isGreaterThan(0);
                            soft.assertThat(service.getMaintenance())
                                    .as("service.maintenance должен быть true или false")
                                    .isIn(true, false);
                            soft.assertThat(service.getMinLimit())
                                    .as("service.min_limit должен быть >= 0")
                                    .isGreaterThanOrEqualTo(0);
                            soft.assertThat(service.getMaxLimit())
                                    .as("service.max_limit должен быть >= min_limit")
                                    .isGreaterThanOrEqualTo(service.getMinLimit());
                            soft.assertThat(service.getCardTypes())
                                    .as("service.card_types не должен быть пустым")
                                    .isNotNull()
                                    .isNotEmpty();
                        });
            });
        });
    }


    @Test
    
    @DisplayName("[regular.payments.on] Включение регулярных платежей")
    void turnOnRegularPayments() {
        step("[regular.payments.on] Включение регулярных платежей", () -> {
            RegularPaymentsOnResponse response = regularPaymentsListService.turnOnRegularPayments(
                    RegularPaymentsOnResponse.class,
                    headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getFirst().getFirst().getError()).isEqualTo(0);
                soft.assertThat(response.getResult().getFirst().getFirst().getError_note())
                        .isEqualTo("Услуга успешно включена");
            });
        });
    }

    @Test
    
    @DisplayName("[regular.payments.on] Включение регулярных платежей без сессии")
    @SkipAuthSetup
    void turnOnRegularPaymentsWithoutSession() {
        step("[regular.payments.on] Включение регулярных платежей", () -> {
            BaseErrorResponse response = regularPaymentsListService.turnOnRegularPayments(BaseErrorResponse.class,
                    emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_BLOCKED);
            });
        });
    }

    @Test
    
    @DisplayName("[regular.payments.off] Отключение регулярных платежей")
    void turnOffRegularPayments() {
        step("[regular.payments.off] Отключение регулярных платежей", () -> {
            RegularPaymentsOfResponse response = regularPaymentsListService.turnOffRegularPayments(
                    RegularPaymentsOfResponse.class,
                    headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getFirst().getFirst().getError()).isEqualTo(0);
                soft.assertThat(response.getResult().getFirst().getFirst().getErrorNote())
                        .isEqualTo("Услуга успешно отключена");
            });
        });
    }

    @Test
    
    @DisplayName("[regular.payments.off] Отключение регулярных платежей без сессии")
    @SkipAuthSetup
    void turnOffRegularPaymentsWithoutSession() {
        step("[regular.payments.off] Отключение регулярных платежей", () -> {
            BaseErrorResponse response = regularPaymentsListService.turnOffRegularPayments(BaseErrorResponse.class,
                    emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_BLOCKED);
            });
        });
    }
}
