package com.example.tests.ofd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.ofd.models.merchant_ms.QRCodeRequest;
import com.example.api.ofd.services.MerchantService;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class MerchantTest extends OfdApiBaseTest {

    @Autowired
    MerchantService merchantService;

    
    @DisplayName("[v3/merchant/qrcode][POST] Отправка qr линков фискализированных чеков")
    @ParameterizedTest
    @CsvSource({
            "https://ofd.soliq.uz/epi?t=EP000000000354&r=711800053&c=20241230153742&s=401572256443, 465, 200",
            "https://ofd.soliq.uz/epi?t=EP000000000354&r=711800053&c=20241230153742&s=401572256443, -1, 400",
            "abcd, 465, 400",
    })
    void qrCodeLinkTest(String qrCodeUrl, int paymentId, int statusCode) {
        QRCodeRequest createQRLinkRequest = QRCodeRequest.builder()
                .qrCodeURL(qrCodeUrl)
                .paymentId(paymentId)
                .build();
        var responseQRCode = step("отправить qr линк фискализированного чека", () ->
                merchantService.sendQRLinks(createQRLinkRequest));
        step("Проверка ответа", () -> {
            soft.assertThat(responseQRCode.statusCode()).isEqualTo(statusCode);
        });
    }

    @Test
    
    @DisplayName("[v3/merchant/products][POST] Отправка данных товаров и услуг")
    void productsTest() {
        var responseProducts =
                step("Отправить данных товаров/услуг", () -> merchantService.sendProductsInfo());
        step("Проверка ответа", () -> {
            soft.assertThat(responseProducts.statusCode()).isEqualTo(200);
        });
    }
}
