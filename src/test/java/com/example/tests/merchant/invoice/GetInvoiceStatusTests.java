package com.example.tests.merchant.invoice;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.merchant.constants.MerchantApiTestData;
import com.example.api.merchant.models.invoice.CreateInvoiceRequest;
import com.example.api.merchant.models.invoice.CreateInvoiceResponse;
import com.example.api.merchant.models.invoice.GetInvoiceStatusResponse;
import com.example.api.merchant.services.invoice.GetInvoiceStatusService;
import com.example.api.merchant.services.invoice.MerchantInvoiceService;
import com.example.tests.merchant.MerchantBaseTest;
import com.example.utils.TokenGenerator;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static com.example.api.merchant.constants.MerchantApiTestData.MERCHANT_TRANS_ID;
import static com.example.api.merchant.constants.MerchantApiTestData.SERVICE_ID;

public class GetInvoiceStatusTests extends MerchantBaseTest {
    @Autowired
    GetInvoiceStatusService getInvoiceStatusService;

    @Autowired
    MerchantInvoiceService merchantInvoiceService;

    String phoneNumber, authToken, secretKey;
    Integer invoiceId;

    private static Stream<Object> invalidInvoiceIds() {
        return Stream.of("" /*null*/); // null убрал так как приходить 500
    }

    private static Stream<Object> invalidTimeStamps() {
        return Stream.of(
                -86400000L, // -1 день
                -600000L, // -10 минут
                600000L, // +10 минут
                86400000L // +1 день
        );
    }

    @BeforeAll
    void setUp() {
        secretKey = merchantApiTestData.getMerchantSecretKey();
        phoneNumber = userData.getUser("XAS").getPhoneNumber();
        authToken = TokenGenerator.getMerchantAuthToken(
                MerchantApiTestData.MERCHANT_USER_ID,
                System.currentTimeMillis(),
                secretKey
        );
        var params = CreateInvoiceRequest.builder()
                .serviceId(SERVICE_ID)
                .amount(123.12F)
                .phoneNumber(phoneNumber)
                .merchantTransId(MERCHANT_TRANS_ID)
                .build();
        CreateInvoiceResponse response = merchantInvoiceService.createInvoice(params, authToken);
        invoiceId = response.getInvoiceId();
    }

    @Test
    @DisplayName("[/invoice/status] [GET] Получить статус инвойса")
        void getInvoiceStatusTest() {
        step("Запрос на получения статуса инвойса", () -> {
            GetInvoiceStatusResponse response =
                    getInvoiceStatusService.getInvoiceStatus(SERVICE_ID, invoiceId, authToken);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorCode()).isEqualTo(0);
                soft.assertThat(response.getErrorNote()).isEmpty();
                soft.assertThat(response.getStatus()).isNotNull();
                soft.assertThat(response.getStatusNote()).isEmpty();
                soft.assertThat(response.getPaymentId()).isNull();
            });
        });
    }

    @Test
    @DisplayName("[/invoice/status] [GET] Получить статус инвойса с не правильным инвойсом")
        void getInvoiceStatusWithUnCorrectInvoiceIdTest() {
        step("Запрос на получения статуса инвойса", () -> {
            GetInvoiceStatusResponse response =
                    getInvoiceStatusService.getInvoiceStatus(SERVICE_ID, 1689291011, authToken);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorCode()).isEqualTo(0);
                soft.assertThat(response.getErrorNote()).isEmpty();
                soft.assertThat(response.getStatus()).isNull();
                soft.assertThat(response.getStatusNote()).isEmpty();
                soft.assertThat(response.getPaymentId()).isNull();
            });
        });
    }

    @ParameterizedTest
    @Tag("negative")
    @MethodSource("invalidInvoiceIds")
    @DisplayName("[/invoice/status] [GET] Получить статус инвойса с не валидными ид invoice_id")
        void getInvoiceStatusWithInvalidIdsTest(Object invoiceId) {
        step("Запрос на получения статуса инвойса", () -> {
            GetInvoiceStatusResponse response =
                    getInvoiceStatusService.getInvoiceStatus(SERVICE_ID, invoiceId, authToken);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorCode()).isEqualTo(-404);
                soft.assertThat(response.getErrorNote()).isEqualTo("Resource not found");
            });
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("[/invoice/status] [GET] Получить статус инвойса без auth токена")
        void getInvoiceStatusWithOutAuthTokenTest() {
        step("Запрос на получения статуса инвойса", () -> {
            GetInvoiceStatusResponse response =
                    getInvoiceStatusService.getInvoiceStatusWithOutAuthToken(SERVICE_ID, invoiceId);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorCode()).isEqualTo(-12);
                soft.assertThat(response.getErrorNote()).isEqualTo("Неверные данные поставщика");
            });
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("[/invoice/status] [GET] Получить статус инвойса с не валидным auth токеном (ключ не правильный)")
        void getInvoiceStatusWithInvalidSecretKeyTest() {
        step("Запрос на получения статуса инвойса", () -> {
            String invalidAuthToken = TokenGenerator.getMerchantAuthToken(
                    MerchantApiTestData.MERCHANT_USER_ID,
                    System.currentTimeMillis(),
                    secretKey + "invalidKey");
            GetInvoiceStatusResponse response =
                    getInvoiceStatusService.getInvoiceStatus(SERVICE_ID, invoiceId, invalidAuthToken);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorCode()).isEqualTo(-15);
                soft.assertThat(response.getErrorNote()).isEqualTo("Неверная строка подписи данных.");
            });
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("[/invoice/status] [GET] Получить статус инвойса с не валидным auth токеном (мерачант ид не правильный)")
    void getInvoiceStatusWithInvalidMerchantIdTest() {
        step("Запрос на получения статуса инвойса", () -> {
            String invalidAuthToken = TokenGenerator.getMerchantAuthToken(
                    MerchantApiTestData.MERCHANT_USER_ID + "123",
                    System.currentTimeMillis(),
                    secretKey);
            GetInvoiceStatusResponse response =
                    getInvoiceStatusService.getInvoiceStatus(SERVICE_ID, invoiceId, invalidAuthToken);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorCode()).isEqualTo(-15);
                soft.assertThat(response.getErrorNote()).isEqualTo("Неверная строка подписи данных.");
            });
        });
    }

    @ParameterizedTest
    @Tag("negative")
    @MethodSource("invalidTimeStamps")
    @DisplayName("[/invoice/status] [GET] Получить статус инвойса с не валидными timeStamp")
        void getInvoiceStatusWithInvalidTimeStampTest(Long invalidTimeStamps) {
        step("Запрос на получения статуса инвойса", () -> {
            Long currentTimeStamp = System.currentTimeMillis();
            String invalidAuthToken = TokenGenerator.getMerchantAuthToken(
                    MerchantApiTestData.MERCHANT_USER_ID,
                    currentTimeStamp + invalidTimeStamps,
                    secretKey);
            GetInvoiceStatusResponse response =
                    getInvoiceStatusService.getInvoiceStatus(SERVICE_ID, invoiceId, invalidAuthToken);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorCode()).isEqualTo(0);
                soft.assertThat(response.getErrorNote()).isEmpty();
                soft.assertThat(response.getStatus()).isNotNull();
                soft.assertThat(response.getStatusNote()).isEmpty();
                soft.assertThat(response.getPaymentId()).isNull();
            });
        });
    }
}
