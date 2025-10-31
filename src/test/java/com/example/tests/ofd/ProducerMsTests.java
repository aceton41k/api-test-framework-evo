package com.example.tests.ofd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.ofd.models.producer_ms.ClickPaymentRefundRequest;
import com.example.api.ofd.models.producer_ms.ClickPaymentRequest;
import com.example.api.ofd.models.producer_ms.ProducerFiscallingStatusItemResponse;
import com.example.api.ofd.models.producer_ms.ProducerStatusItemResponse;
import com.example.api.ofd.services.ProducerMSService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.step;
import static org.awaitility.Awaitility.await;

public class ProducerMsTests extends OfdApiBaseTest {

    @Autowired
    ProducerMSService producerMSService;

    @Test
    
    @DisplayName("[v1/click-payment][POST] Фискализация платежного чека по paydocID и проверка его статуса с статуса фискализации")
    void fiscalingPaymentTicket() {
        Integer paydocId = Math.toIntExact(System.currentTimeMillis() & 0x7fffffff);

        step("Отправить запрос на фискализации платежа", () -> {
            var request = ClickPaymentRequest.builder().paydocId(paydocId).build();
            var response = producerMSService.fiscalingPaymentTicket(request);
            step("Проверка ответа", () -> {
                //тело нет в ответе вот и проверки тоже нет из-за этого
                soft.assertThat(response.statusCode()).isEqualTo(200);
            });
        });

        step("Отправить запрос на проверку статуса платежа", () -> {
            await().pollInterval(2, TimeUnit.SECONDS).atMost(20, TimeUnit.SECONDS).until(() -> {
                var resp = producerMSService.checkStatusByPaydocId(paydocId);
                return "FINISHED".equals(resp.getFirst().getMainStatus());
            });

            List<ProducerStatusItemResponse> response = producerMSService.checkStatusByPaydocId(paydocId);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getFirst().getId()).isNotNull();
                soft.assertThat(response.getFirst().getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getFirst().getPaymentInfo()).isNotNull();
                soft.assertThat(response.getFirst().getMainStatus()).isIn( "FINISHED");
                soft.assertThat(response.getFirst().getCommissionStatus()).isIn("WITHOUT_RECEIPT");
                soft.assertThat(response.getFirst().getCreatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getUpdatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getIsRefund()).isFalse();
                soft.assertThat(response.getFirst().getMainErrorMessage()).isNull();
                soft.assertThat(response.getFirst().getCommissionErrorMessage()).isNull();
            });
        });

        step("Отправить запрос на проверку фискального статуса платежа", () -> {
            ProducerFiscallingStatusItemResponse response = producerMSService.checkFiscallingStatusByPaydocId(paydocId);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorMessage()).isNull();
                soft.assertThat(response.getStatusCode()).isEqualTo(0);
                soft.assertThat(response.getDateTime()).isNotBlank();
                soft.assertThat(response.getResponseBody()).hasSize(1);
                soft.assertThat(response.getResponseBody().getFirst().getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getResponseBody().getFirst().getReceiptId()).isNotNull();
                soft.assertThat(response.getResponseBody().getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getResponseBody().getFirst().getMain()).isTrue();
                soft.assertThat(response.getResponseBody().getFirst().getReceiptType()).isEqualTo(0);
                soft.assertThat(response.getResponseBody().getFirst().getPaymentType()).isEqualTo("PAYMENT_TYPE_1");
                soft.assertThat(response.getResponseBody().getFirst().getDescription())
                    .isEqualTo("Фискализируем платеж сразу сами");
                soft.assertThat(response.getResponseBody().getFirst().getOperationTime()).isNotBlank();
                soft.assertThat(response.getResponseBody().getFirst().getStatus()).isIn("FINISHED");
                soft.assertThat(response.getResponseBody().getFirst().getQrCodeUrl()).isNotBlank();

            });
        });

    }

    @Test
    
    @DisplayName("[v1/click-payment][POST] Фискализация возвратного чека по paydocID и проверка его статуса со статусом фискализации")
    void fiscalingRefundTicketWithoutPaymentTicket() {
        Integer paydocId = Math.toIntExact(System.currentTimeMillis() & 0x7fffffff);

        step("Отправить запрос на фискализации возврата", () -> {
            var request = ClickPaymentRequest.builder().paydocId(paydocId).refund(1).build();
            var response = producerMSService.fiscalingPaymentTicket(request);
            //тело нет в ответе вот и проверки тоже нет из-за этого
            soft.assertThat(response.statusCode()).isEqualTo(200);
        });

        step("Отправить запрос на проверку статуса", () -> {
            await().pollInterval(2, TimeUnit.SECONDS).atMost(20, TimeUnit.SECONDS).until(() -> {
                var resp = producerMSService.checkStatusByPaydocId(paydocId);
                return "FAILED".equals(resp.getFirst().getMainStatus());
            });
            List<ProducerStatusItemResponse> response = producerMSService.checkStatusByPaydocId(paydocId);

            step("Проверка ответа", () -> {
                //проверка возврата
                soft.assertThat(response).hasSize(1);
                soft.assertThat(response.getFirst().getId()).isNotNull();
                soft.assertThat(response.getFirst().getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getFirst().getPaymentInfo()).isNotNull();
                soft.assertThat(response.getFirst().getMainStatus()).isIn("FAILED");
                soft.assertThat(response.getFirst().getCommissionStatus()).isIn("WITHOUT_RECEIPT", "CREATED");
                soft.assertThat(response.getFirst().getCreatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getUpdatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getIsRefund()).isTrue();
                soft.assertThat(response.getFirst().getMainErrorMessage()).isNotBlank();
                soft.assertThat(response.getFirst().getCommissionErrorMessage()).isNull();
            });
        });

        step("Отправить запрос на проверку фискального статуса платежа(возврата)", () -> {
            ProducerFiscallingStatusItemResponse response = producerMSService.checkFiscallingStatusByPaydocId(paydocId);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorMessage()).isNull();
                soft.assertThat(response.getStatusCode()).isEqualTo(0);
                soft.assertThat(response.getDateTime()).isNotBlank();
                soft.assertThat(response.getResponseBody()).isEmpty();
            });
        });

    }

    @Test
    
    @DisplayName("[v1/click-payment][POST] Фискализация платежного чека и возврата по paydocID потом проверка их статусов и статусов фискализации")
    void fiscalingPaymentTicketAndRefundTicket() {
        Integer paydocId = Math.toIntExact(System.currentTimeMillis() & 0x7fffffff);

        step("Отправить запрос на фискализации платежа", () -> {
            var request = ClickPaymentRequest.builder().paydocId(paydocId).build();
            producerMSService.fiscalingPaymentTicket(request);
        });

        step("Отправить запрос на проверку статуса", () -> {
            await().pollInterval(2, TimeUnit.SECONDS).atMost(20, TimeUnit.SECONDS).until(() -> {
                var resp = producerMSService.checkStatusByPaydocId(paydocId);
                return "FINISHED".equals(resp.getFirst().getMainStatus());
            });

            List<ProducerStatusItemResponse> response = producerMSService.checkStatusByPaydocId(paydocId);

            step("Проверка ответа", () -> {
                //проверка платежа
                soft.assertThat(response).hasSize(1);
                soft.assertThat(response.getFirst().getId()).isNotNull();
                soft.assertThat(response.getFirst().getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getFirst().getPaymentInfo()).isNotNull();
                soft.assertThat(response.getFirst().getMainStatus()).isIn("FINISHED");
                soft.assertThat(response.getFirst().getCommissionStatus()).isIn("WITHOUT_RECEIPT");
                soft.assertThat(response.getFirst().getCreatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getUpdatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getIsRefund()).isFalse();
                soft.assertThat(response.getFirst().getMainErrorMessage()).isNull();
                soft.assertThat(response.getFirst().getCommissionErrorMessage()).isNull();
            });
        });

        step("Отправить запрос на фискализации возврата", () -> {
            var request = ClickPaymentRequest.builder().paydocId(paydocId).refund(1).build();
            var response = producerMSService.fiscalingPaymentTicket(request);
            //тело нет в ответе вот и проверки тоже нет из-за этого
            soft.assertThat(response.statusCode()).isEqualTo(200);
        });

        step("Отправить запрос на проверку статуса", () -> {
            await().pollInterval(2, TimeUnit.SECONDS).atMost(20, TimeUnit.SECONDS).until(() -> {
                var resp = producerMSService.checkStatusByPaydocId(paydocId);
                return "FINISHED".equals(resp.get(1).getMainStatus());
            });

            List<ProducerStatusItemResponse> response = producerMSService.checkStatusByPaydocId(paydocId);

            step("Проверка ответа", () -> {
                //проверка платежа
                soft.assertThat(response).hasSize(2);
                soft.assertThat(response.getFirst().getId()).isNotNull();
                soft.assertThat(response.getFirst().getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getFirst().getPaymentInfo()).isNotNull();
                soft.assertThat(response.getFirst().getMainStatus()).isIn("ACTIVE", "FINISHED", "CREATED");
                soft.assertThat(response.getFirst().getCommissionStatus()).isIn("WITHOUT_RECEIPT", "CREATED");
                soft.assertThat(response.getFirst().getCreatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getUpdatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getIsRefund()).isFalse();
                soft.assertThat(response.getFirst().getMainErrorMessage()).isNull();
                soft.assertThat(response.getFirst().getCommissionErrorMessage()).isNull();
                //проверка возврата
                soft.assertThat(response.get(1).getId()).isNotNull();
                soft.assertThat(response.get(1).getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.get(1).getServiceId()).isNotNull();
                soft.assertThat(response.get(1).getPaymentInfo()).isNotNull();
                soft.assertThat(response.get(1).getMainStatus()).isIn("ACTIVE", "FINISHED", "CREATED");
                soft.assertThat(response.get(1).getCommissionStatus()).isIn("WITHOUT_RECEIPT", "CREATED");
                soft.assertThat(response.get(1).getCreatedAt()).isNotBlank();
                soft.assertThat(response.get(1).getUpdatedAt()).isNotBlank();
                soft.assertThat(response.get(1).getIsRefund()).isTrue();
                soft.assertThat(response.get(1).getMainErrorMessage()).isNull();
                soft.assertThat(response.get(1).getCommissionErrorMessage()).isNull();
            });
        });

        step("Отправить запрос на проверку фискального статуса платежа(возврата)", () -> {
            ProducerFiscallingStatusItemResponse response = producerMSService.checkFiscallingStatusByPaydocId(paydocId);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorMessage()).isNull();
                soft.assertThat(response.getStatusCode()).isEqualTo(0);
                soft.assertThat(response.getDateTime()).isNotBlank();
                soft.assertThat(response.getResponseBody()).hasSize(2);
                //платеж
                soft.assertThat(response.getResponseBody().getFirst().getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getResponseBody().getFirst().getReceiptId()).isNotNull();
                soft.assertThat(response.getResponseBody().getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getResponseBody().getFirst().getMain()).isTrue();
                soft.assertThat(response.getResponseBody().getFirst().getReceiptType()).isEqualTo(0);
                soft.assertThat(response.getResponseBody().getFirst().getPaymentType()).isEqualTo("PAYMENT_TYPE_1");
                soft.assertThat(response.getResponseBody().getFirst().getDescription())
                    .isEqualTo("Фискализируем платеж сразу сами");
                soft.assertThat(response.getResponseBody().getFirst().getOperationTime()).isNotBlank();
                soft.assertThat(response.getResponseBody().getFirst().getStatus()).isIn("FINISHED");
                soft.assertThat(response.getResponseBody().getFirst().getQrCodeUrl()).isNotBlank();
                //возврат
                soft.assertThat(response.getResponseBody().get(1).getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getResponseBody().get(1).getReceiptId()).isNotNull();
                soft.assertThat(response.getResponseBody().get(1).getServiceId()).isNotNull();
                soft.assertThat(response.getResponseBody().get(1).getMain()).isTrue();
                soft.assertThat(response.getResponseBody().get(1).getReceiptType()).isEqualTo(0);
                soft.assertThat(response.getResponseBody().get(1).getPaymentType()).isEqualTo("REFUND_TYPE_1");
                soft.assertThat(response.getResponseBody().get(1).getDescription())
                    .isEqualTo("Фискализируем возврат сразу сами, если платеж фискализирован");
                soft.assertThat(response.getResponseBody().get(1).getOperationTime()).isNotBlank();
                soft.assertThat(response.getResponseBody().get(1).getStatus()).isIn("FINISHED");
                soft.assertThat(response.getResponseBody().get(1).getQrCodeUrl()).isNotBlank();

            });
        });
    }

    @Test
    
    @DisplayName("[v1/click-payment][POST] Фискализация моментального платежа(возврата) по paydocID и проверка его статуса")
    void fiscalingMomentRefundTicket() {
        Integer paydocId = java.util.concurrent.ThreadLocalRandom.current().nextInt() & Integer.MAX_VALUE;

        step("Отправить запрос на фискализации платежа", () -> {
            var request = ClickPaymentRequest.builder().paydocId(paydocId).build();
            var response = producerMSService.fiscalingPaymentTicket(request);
            step("Проверка ответа", () -> {
                //тело нет в ответе вот и проверки тоже нет из-за этого
                soft.assertThat(response.statusCode()).isEqualTo(200);
            });
        });

        //нужно обязательно проверять статус
        step("Отправить запрос на проверку статуса", () -> {
            await().pollInterval(2, TimeUnit.SECONDS).atMost(20, TimeUnit.SECONDS).until(() -> {
                var resp = producerMSService.checkStatusByPaydocId(paydocId);
                return "FINISHED".equals(resp.getFirst().getMainStatus());
            });

            List<ProducerStatusItemResponse> response = producerMSService.checkStatusByPaydocId(paydocId);

            step("Проверка ответа", () -> {
                //проверка платежа
                soft.assertThat(response).hasSize(1);
                soft.assertThat(response.getFirst().getId()).isNotNull();
                soft.assertThat(response.getFirst().getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getFirst().getPaymentInfo()).isNotNull();
                soft.assertThat(response.getFirst().getMainStatus()).isIn("FINISHED");
                soft.assertThat(response.getFirst().getCommissionStatus()).isIn("WITHOUT_RECEIPT");
                soft.assertThat(response.getFirst().getCreatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getUpdatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getIsRefund()).isFalse();
                soft.assertThat(response.getFirst().getMainErrorMessage()).isNull();
                soft.assertThat(response.getFirst().getCommissionErrorMessage()).isNull();
            });
        });

        step("Отправить запрос на фискализации моментального возврата", () -> {
            var request = ClickPaymentRefundRequest.builder().paydocId(paydocId).refund(1).instantRefund(1).build();
            var response = producerMSService.fiscalingRefundPaymentTicket(request);
            step("Проверка ответа", () -> {
                //тело нет в ответе вот и проверки тоже нет из-за этого
                soft.assertThat(response.statusCode()).isEqualTo(200);
            });
        });

        step("Отправить запрос на проверку статуса", () -> {
            await().pollInterval(2, TimeUnit.SECONDS).atMost(20, TimeUnit.SECONDS).until(() -> {
                var resp = producerMSService.checkStatusByPaydocId(paydocId);
                return "FINISHED".equals(resp.get(1).getMainStatus());
            });

            List<ProducerStatusItemResponse> response = producerMSService.checkStatusByPaydocId(paydocId);

            step("Проверка ответа", () -> {
                //проверка платежа
                soft.assertThat(response).hasSize(2);
                soft.assertThat(response.getFirst().getId()).isNotNull();
                soft.assertThat(response.getFirst().getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getFirst().getPaymentInfo()).isNotNull();
                soft.assertThat(response.getFirst().getMainStatus()).isIn("ACTIVE", "FINISHED", "CREATED");
                soft.assertThat(response.getFirst().getCommissionStatus()).isIn("WITHOUT_RECEIPT", "CREATED");
                soft.assertThat(response.getFirst().getCreatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getUpdatedAt()).isNotBlank();
                soft.assertThat(response.getFirst().getIsRefund()).isFalse();
                soft.assertThat(response.getFirst().getMainErrorMessage()).isNull();
                soft.assertThat(response.getFirst().getCommissionErrorMessage()).isNull();
                //проверка возврата
                soft.assertThat(response.get(1).getId()).isNotNull();
                soft.assertThat(response.get(1).getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.get(1).getServiceId()).isNotNull();
                soft.assertThat(response.get(1).getPaymentInfo()).isNotNull();
                soft.assertThat(response.get(1).getMainStatus()).isIn("ACTIVE", "FINISHED", "CREATED");
                soft.assertThat(response.get(1).getCommissionStatus()).isIn("WITHOUT_RECEIPT", "CREATED");
                soft.assertThat(response.get(1).getCreatedAt()).isNotBlank();
                soft.assertThat(response.get(1).getUpdatedAt()).isNotBlank();
                soft.assertThat(response.get(1).getIsRefund()).isTrue();
                soft.assertThat(response.get(1).getMainErrorMessage()).isNull();
                soft.assertThat(response.get(1).getCommissionErrorMessage()).isNull();
            });
        });

        step("Отправить запрос на проверку фискального статуса платежа(возврата)", () -> {
            ProducerFiscallingStatusItemResponse response = producerMSService.checkFiscallingStatusByPaydocId(paydocId);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getErrorMessage()).isNull();
                soft.assertThat(response.getStatusCode()).isEqualTo(0);
                soft.assertThat(response.getDateTime()).isNotBlank();
                soft.assertThat(response.getResponseBody()).hasSize(2);
                //платеж
                soft.assertThat(response.getResponseBody().getFirst().getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getResponseBody().getFirst().getReceiptId()).isNotNull();
                soft.assertThat(response.getResponseBody().getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getResponseBody().getFirst().getMain()).isTrue();
                soft.assertThat(response.getResponseBody().getFirst().getReceiptType()).isEqualTo(0);
                soft.assertThat(response.getResponseBody().getFirst().getPaymentType()).isEqualTo("PAYMENT_TYPE_1");
                soft.assertThat(response.getResponseBody().getFirst().getDescription())
                    .isEqualTo("Фискализируем платеж сразу сами");
                soft.assertThat(response.getResponseBody().getFirst().getOperationTime()).isNotBlank();
                soft.assertThat(response.getResponseBody().getFirst().getStatus()).isIn("FINISHED");
                soft.assertThat(response.getResponseBody().getFirst().getQrCodeUrl()).isNotBlank();
                //возврат
                soft.assertThat(response.getResponseBody().get(1).getPaydocId()).isEqualTo(paydocId);
                soft.assertThat(response.getResponseBody().get(1).getReceiptId()).isNotNull();
                soft.assertThat(response.getResponseBody().get(1).getServiceId()).isNotNull();
                soft.assertThat(response.getResponseBody().get(1).getMain()).isTrue();
                soft.assertThat(response.getResponseBody().get(1).getReceiptType()).isEqualTo(0);
                soft.assertThat(response.getResponseBody().get(1).getPaymentType()).isEqualTo("REFUND_TYPE_1");
                soft.assertThat(response.getResponseBody().get(1).getDescription())
                    .isEqualTo("Фискализируем возврат сразу сами, если платеж фискализирован");
                soft.assertThat(response.getResponseBody().get(1).getOperationTime()).isNotBlank();
                soft.assertThat(response.getResponseBody().get(1).getStatus()).isIn("FINISHED");
                soft.assertThat(response.getResponseBody().get(1).getQrCodeUrl()).isNotBlank();

            });
        });

    }

}
