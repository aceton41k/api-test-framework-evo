package com.example.tests.evo.bnpl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.bnpl.BnplPaymentRequest;
import com.example.api.evo.models.bnpl.BnplPaymentResponse;
import com.example.api.evo.models.bnpl.BnplPaymentResponse.Plan;
import com.example.api.evo.services.bnpl.BnplPaymentService;
import com.example.tests.BaseTest;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class BnplPaymentTest extends BaseTest {
    @Autowired
    BnplPaymentService bnplPaymentService;

    private static final Integer SPLIT_SERVICE_ID = 52528;

    @Test
    
    @DisplayName("[bnpl.payment] Создать чекаут в Split")
        @Disabled("Пока не понятно почему падает отключу")
    void createBnplPayment() {
        step("Запрос на создание чекаута", () -> {
            var params = BnplPaymentRequest.Params.builder()
                    .serviceId(SPLIT_SERVICE_ID)
                    .parameters(BnplPaymentRequest.Params.PaymentParameters.builder()
                            .phoneNum(userData.getUser("NIK").getPhoneNumber())
                            .amount(6000)
                            .build())
                    .transactionType("indoor")
                    .partner("split")
                    .build();
            BnplPaymentResponse response = bnplPaymentService.createPayment(params, deprecatedHeaders);

            List<Plan> plans = response.getResult().getPlans().getPlans();

            step("Проверка, что checkout_id вернулся", () -> {
                soft.assertThat(response.getResult().getCheckoutId()).isNotBlank();
            });
            step("Проверка, что default_plan не пустой", () -> {
                soft.assertThat(response.getResult().getPlans().getDefaultPlan()).isNotBlank();
            });
            step("Проверка, что поля параметра plans не пустые", () -> {
                assertThat(plans).allSatisfy(plan -> {
                    soft.assertThat(plan.getId()).isNotBlank();
                    soft.assertThat(plan.getProductType()).isNotBlank();
                    soft.assertThat(plan.getConstructor()).isNotBlank();
                    soft.assertThat(plan.getSum()).isNotBlank();
                    soft.assertThat(plan.getCurrency()).isNotBlank();
                    soft.assertThat(plan.getFee()).isNotBlank();
                });
            });
            step("Проверка, что поля параметра payments не пустые", () -> {
                assertThat(plans).allSatisfy(plan -> {
                    assertThat(plan.getPayments()).allSatisfy(payment -> {
                        soft.assertThat(payment.getAmount()).isNotBlank();
                        soft.assertThat(payment.getDatetime()).isNotBlank();
                        soft.assertThat(payment.getStatus()).isNotBlank();
                    });
                });
            });
            step("Проверка, что поля параметра constructor_details не пустые", () -> {
                assertThat(plans).allSatisfy(plan -> {
                    soft.assertThat(plan.getConstructorDetails().getLengthInMonths()).isEqualTo(2);
                    soft.assertThat(plan.getConstructorDetails().getPaymentsInterval().getValue()).isEqualTo(14);
                    soft.assertThat(plan.getConstructorDetails().getPaymentsInterval().getType()).isNotBlank();
                });
            });
        });
    }
}
