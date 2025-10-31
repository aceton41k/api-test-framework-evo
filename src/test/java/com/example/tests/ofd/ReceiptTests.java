package com.example.tests.ofd;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.ofd.models.receipt_ms.GetSelectedQRCodesRequest;
import com.example.api.ofd.models.receipt_ms.GetSelectedQRCodesResponse;
import com.example.api.ofd.models.receipt_ms.GetSelectedReceiptInfoRequest;
import com.example.api.ofd.models.receipt_ms.GetSelectedReceiptInfoResponse;
import com.example.api.ofd.services.ReceiptService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.qameta.allure.Allure.step;

public class ReceiptTests extends OfdApiBaseTest {

    @Autowired
    ReceiptService receiptService;

    @Test
    
    @DisplayName("[v2/payment/getSelectedQRCodes][POST] Получения выбранных QR link OFD")
    void getSelectedQRCodesTest() {
        GetSelectedQRCodesRequest request = GetSelectedQRCodesRequest.builder().paymentIds(List.of(PAYMENT_ID)).build();

        step("Отправить запрос на: v2/payment/getSelectedQRCodes", () -> {
            GetSelectedQRCodesResponse response = receiptService.getSelectedQRCodes(request);

            step("Проверить ответ", () -> {
                soft.assertThat(response.getResponseBody().getFirst().getPaymentId()).isEqualTo(PAYMENT_ID);
                soft.assertThat(response.getResponseBody().getFirst().getQrCodeURL()).isEqualTo(RECEIPT_QR_CODE_URL);
                soft.assertThat(response.getStatusCode()).isEqualTo(0);
                soft.assertThat(response.getErrorMessage()).isNull();
                soft.assertThat(response.getDateTime().truncatedTo(ChronoUnit.MINUTES)).isEqualTo(LocalDateTime.now(ZoneId.of("Asia/Tashkent")).truncatedTo(ChronoUnit.MINUTES));

            });
        });
    }

    @Test
    
    @DisplayName("[v2/payment/getSelectedReceiptInfo][GET] Получения данных выбранных чеков OFD")
    void getSelectedReceiptInfoTest() {
        GetSelectedReceiptInfoRequest request = GetSelectedReceiptInfoRequest.builder()
                .paymentId(PAYMENT_ID)
                .operationTime(LocalDateTime.parse(OPERATION_DATETIME))
                .build();

        step("Отправить запрос на:   v2/payment/getSelectedReceiptInfo", () -> {
            GetSelectedReceiptInfoResponse response = receiptService.getSelectedReceiptInfo(request);

            step("Проверить ответ", () -> {
                soft.assertThat(response.getResponseBody().getFirst().getReceiptId()).isEqualTo(RECEIPT_ID);
                soft.assertThat(response.getResponseBody().getFirst().getReceivedCash()).isEqualTo(RECEIVED_CASH);
                soft.assertThat(response.getResponseBody().getFirst().getReceivedECash()).isEqualTo(RECEIVEDE_CASH);
                soft.assertThat(response.getResponseBody().getFirst().getReceivedCard()).isEqualTo(240000);
                soft.assertThat(response.getResponseBody().getFirst().getTime()).isEqualTo(OPERATION_DATETIME);
                soft.assertThat(response.getResponseBody().getFirst().getTotalVAT()).isEqualTo(TOTAL_VAT);
                soft.assertThat(response.getResponseBody().getFirst().getIsRefund()).isEqualTo(IS_REFUND);
                soft.assertThat(response.getResponseBody().getFirst().getReceiptType()).isEqualTo(RECEIPT_TYPE);
                soft.assertThat(response.getResponseBody().getFirst().getTerminalId()).isEqualTo(TERMINAL_ID);
                soft.assertThat(response.getResponseBody().getFirst().getMessageType()).isEqualTo(MESSAGE_TYPE);
                soft.assertThat(response.getResponseBody().getFirst().getItems().getFirst()).isNotNull();
                soft.assertThat(response.getResponseBody().getFirst().getItems().getFirst().getCommissionInfo().getTin()).isEqualTo(TIN);
                soft.assertThat(response.getErrorMessage()).isNull();
                soft.assertThat(response.getStatusCode()).isEqualTo(STATUS_CODE);
                soft.assertThat(response.getDateTime().truncatedTo(ChronoUnit.MINUTES)).isEqualTo(LocalDateTime.now(ZoneId.of("Asia/Tashkent")).truncatedTo(ChronoUnit.MINUTES));
            });
        });
    }

    @Test
    
    @DisplayName("[v2/payment/getSelectedReceiptInfo][GET] Получения данных выбранных чеков OFD негативныq тест")
    void getSelectedReceiptInfoNegativeTest() {
        GetSelectedReceiptInfoRequest request = GetSelectedReceiptInfoRequest.builder()
                .paymentId(123l) //заглушка
                .operationTime(LocalDateTime.parse("2025-05-30T17:44:40.18")) //заглушка
                .build();

        step("Отправить запрос на:   v2/payment/getSelectedReceiptInfo", () -> {
            GetSelectedReceiptInfoResponse response = receiptService.getSelectedReceiptInfo(request);

            step("Проверить ответ", () -> {
                soft.assertThat(response.getStatusCode()).isEqualTo(4);
            });
        });
    }

}
