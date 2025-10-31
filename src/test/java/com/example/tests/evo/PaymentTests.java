package com.example.tests.evo;

import com.example.api.evo.models.payment.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.UserData;
import uz.click.evo_api.models.payment.*;
import com.example.api.evo.services.payments.PaymentService;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.AccountData.XAS_PAYMENT_ID;
import static com.example.api.evo.constants.ServiceData.SERVICE_ID_INTERNET_PACKAGES;
import static com.example.api.evo.constants.ServiceData.SERVICE_ID_UMS;

public class PaymentTests extends EvoBaseTest {
    @Autowired
    PaymentService paymentService;

    @Test
    
    @DisplayName("[payment.mobile.data] Информация для оплаты мобильной связи")
    void paymentMobileData() {
        step("[payment.mobile.data] Информация для оплаты мобильной связи", () -> {
            var params = PaymentMobileDataRequest.Params.builder()
                    .phoneNumber(phoneNumber)
                    .build();
            PaymentMobileDataResponse response = paymentService.getMobilePaymentData(params,
                    headers);

            step("Проверка ответа", () -> {
                assertThat(response.getResult().getService().getId()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[friend.help.request] Помощь друга")
    void friendHelpRequest() {
        UserData.User nik = userData.getUser("NIK");
        step("[friend.help.request] Помощь друга", () -> {
            var parameters = FriendHelpRequest.Parameters.builder()
                    .amount("1001")
                    .phoneNum(phoneNumber)
                    .build();

            var params = FriendHelpRequest.Params.builder()
                    .serviceId(SERVICE_ID_UMS)
                    .phoneNumber(nik.getPhoneNumber())
                    .parameters(parameters)
                    .build();

            FriendHelpResponse response = paymentService.friendHelp(params, headers);

            step("Проверка ответа", () -> {
                assertThat(response.getResult().getInvoiceId()).isNotNull();
                assertThat(response.getResult().getInvoiceId()).isNotEqualTo(0);
            });
        });
    }

    @Test
    
    @DisplayName("[payment.data] Получить форму сервиса")
    void paymentData() {
        step("[payment.data] Получить форму сервиса", () -> {
            var params = PaymentDataRequest.Params.builder()
                    .serviceId(SERVICE_ID_UMS)
                    .step(1)
                    .build();
            PaymentDataResponse response = paymentService.getPaymentData(params, headers);

            step("Проверка ответа", () -> {
                assertThat(response.getResult().getTitle()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[form.data] Получение информацию о форме")
    void formData() {
        step("[form.data] Получение информацию о форме", () -> {
            var params = FormDataRequest.Params.builder()
                    .serviceId(SERVICE_ID_INTERNET_PACKAGES)
                    .step(1)
                    .params(List.of("internet_package"))
                    .build();
            FormDataResponse response = paymentService.getFormData(params, headers);

            step("Проверка ответа", () -> {
                assertThat(response.getResult().getInternetPackage()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[payment.status] Статус платежа")
    @Disabled("Пока отключу так как перед тестом нужно создать платеж")
    void paymentStatus() {
        step("[payment.status] Статус платежа", () -> {
            var params = PaymentStatusRequest.Params.builder()
                    .paymentId(XAS_PAYMENT_ID)
                    .build();
            PaymentStatusResponse response = paymentService.getPaymentStatus(params, headers);

            step("Проверка ответа", () -> {
                assertThat(response.getResult().getPaymentId()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[latest.payments.get] Получения последних платежей")
    @Disabled("пока отключу так как что бы метод работал у юзера должны быть оплаты")
    void getLatestPayments() {
        step("Получить последние платежи", () -> {
            GetLatestPaymentStatusResponse response = paymentService.getLatestPaymentStatus(headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Список истории не должен быть пустым")
                        .isNotEmpty()
                        .allSatisfy(item -> {
                            soft.assertThat(item.getPaymentId())
                                    .as("payment_id должен быть не пустым")
                                    .isNotBlank();
                            soft.assertThat(item.getParameter())
                                    .as("parameter должен быть не пустым")
                                    .isNotBlank();
                            soft.assertThat(item.getAmount())
                                    .as("amount должен быть положительным")
                                    .isGreaterThan(0);
                            soft.assertThat(item.getDatetime())
                                    .as("datetime должен быть больше 0")
                                    .isGreaterThan(0);
                            var service = item.getService();
                            soft.assertThat(service).isNotNull();
                            soft.assertThat(service.getId()).isGreaterThan(0);
                            soft.assertThat(service.getName()).isNotBlank();
                            soft.assertThatUrl(service.getImage()).isReachable();
                            soft.assertThat(service.getCategoryId()).isGreaterThan(0);
                            soft.assertThat(service.getCardTypes()).isNotEmpty();
                            soft.assertThat(service.getMinLimit()).isLessThanOrEqualTo(service.getMaxLimit());
                        });
            });
        });
    }
}
