package com.example.tests.bnpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.bnpl.models.CheckoutWithPartnerRequest;
import com.example.api.bnpl.services.BnplApiCheckoutService;

import static io.qameta.allure.Allure.step;

public class BnplCheckoutTest extends BnplBaseTest{
    @Autowired
    BnplApiCheckoutService bnplApiCheckoutService;

    @Test
    
    @DisplayName("[/api/internal/v2/checkout][POST] Успешное создание checkout партнёра Split")
    void getCheckoutForSplitByPartner() {
        step("Запрос на создание чекаута для Split", () -> {
            var bodyParams = CheckoutWithPartnerRequest.builder()
                    .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                    .merchantServiceName(SHISHKA_MERCHANT_NAME)
                    .bnplPartner(SPLIT_PROVIDER)
                    .build();

            var checkoutResponse = bnplApiCheckoutService.getCheckout(bodyParams);

            step("Проверка ответа", () -> {
                soft.assertThat(checkoutResponse.getStatus()).isEqualTo("Success");
                soft.assertThat(checkoutResponse.getMessage()).isEqualTo("Checkout successful.");

                var data = checkoutResponse.getData();

                soft.assertThat(data.getProviderName()).isEqualTo(SPLIT_PROVIDER);
                soft.assertThat(data.getFrontendProviderUrl()).isNotEmpty();
                soft.assertThat(data.getStatusCode()).isEqualTo(0);
                soft.assertThat(data.getStatusDescription()).isEqualTo(null);

                var application = data.getApplication();

                soft.assertThat(application.getProviderId()).isEqualTo(4);
                soft.assertThat(application.getCheckoutId()).isNotEmpty();
                soft.assertThat(application.getTransactionType()).isEqualTo("indoor");
                soft.assertThat(application.getInternalMerchantServiceId()).isEqualTo(SHISHKA_LOUNGE_MERCHANT_ID);
                soft.assertThat(application.getInternalMerchantServiceName()).isEqualTo(SHISHKA_MERCHANT_NAME);
                soft.assertThat(application.getInternalGeo()).isNotEmpty();
                soft.assertThat(application.getInternalTotalAmount()).isEqualTo(10000);
                soft.assertThat(application.getInternalDeviceId()).isEqualTo("123456789");
                soft.assertThat(application.getBnplUserId()).isNotNull();
                soft.assertThat(application.getBnplStatusId()).isNotNull();
                soft.assertThat(application.getBnplServiceId()).isNotNull();
                soft.assertThat(application.getInternalBasket()).isNotEmpty();
                soft.assertThat(application.getFee()).isEqualTo("25");
                soft.assertThat(application.getUpdatedAt()).isNotEmpty();
                soft.assertThat(application.getCreatedAt()).isNotEmpty();
                soft.assertThat(application.getId()).isNotNull();

                var res = application.getResAtr().getRes();

                soft.assertThat(res.getDefaultPlan()).isNotEmpty();

                var plan = res.getPlans().getFirst();

                soft.assertThat(plan.getId()).isNotEmpty();
                soft.assertThat(plan.getProductType()).isEqualTo("base_split_uz");
                soft.assertThat(plan.getConstructor()).isEqualTo("split_2_month_afterpay_uz");
                soft.assertThat(plan.getSum()).isNotEmpty();
                soft.assertThat(plan.getCurrency()).isEqualTo("UZS");
                soft.assertThat(plan.getFee()).isEqualTo("25.00");

                var firstPayment = plan.getPayments().getFirst();

                soft.assertThat(firstPayment.getAmount()).isEqualTo("250000.00");
                soft.assertThat(firstPayment.getDatetime()).isNotEmpty();
                soft.assertThat(firstPayment.getStatus()).isEqualTo("expected");

                var secondPayment = plan.getPayments().get(2);

                soft.assertThat(secondPayment.getStatus()).isEqualTo("coming");

                var constructor = plan.getConstructorDetails();

                soft.assertThat(constructor.getLengthInMonths()).isEqualTo(2);
                soft.assertThat(constructor.getPaymentsInterval().getValue()).isEqualTo(14);
                soft.assertThat(constructor.getPaymentsInterval().getType()).isEqualTo("day");

                var req = application.getReqAtr();

                soft.assertThat(req.getCheckoutId()).isNotEmpty();
                soft.assertThat(req.getUserId()).isEqualTo("2459517");

                var checkout = req.getCheckout();

                soft.assertThat(checkout.getAmount()).isEqualTo("10000");
                soft.assertThat(checkout.getCurrency()).isEqualTo("UZS");

                var geo = checkout.getGeo();

                soft.assertThat(geo.getLatitude()).isEmpty();
                soft.assertThat(geo.getLongitude()).isEmpty();

                var merchant = checkout.getMerchant();

                soft.assertThat(merchant.getName()).isEqualTo(SHISHKA_MERCHANT_NAME);
                soft.assertThat(merchant.getId()).isEqualTo("52527");
                soft.assertThat(merchant.getLogo()).isEqualTo("https://example.com/logo.png");

                var items = checkout.getItems().getFirst();

                soft.assertThat(items.getId()).isEqualTo("0");
                soft.assertThat(items.getName()).isEqualTo(SHISHKA_MERCHANT_NAME);
                soft.assertThat(items.getCount()).isEqualTo(1);
                soft.assertThat(items.getAmount()).isEqualTo("10000");
                soft.assertThat(items.getCurrency()).isEqualTo("UZS");
                soft.assertThat(items.getTotalAmount()).isEqualTo("10000");
            });
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {ZERO_AMOUNT, MAX_INCORRECT_AMOUNT_FOR_SPLIT_PROVIDER, MAX_NEGATIVE_AMOUNT})
    
    @DisplayName("[/api/internal/v2/checkout][POST] Ошибка при отправке запроса со значением amount меньше допустимого")
    void getCheckoutWithIncorrectAmount(int amount) {
        var bodyParams = CheckoutWithPartnerRequest.builder()
                .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                .merchantServiceName(SHISHKA_MERCHANT_NAME)
                .bnplPartner(SPLIT_PROVIDER)
                .totalAmount(amount)
                .build();

        var checkoutResponse = bnplApiCheckoutService.getValidationError(bodyParams);

        step("Проверка ответа", () -> {
            soft.assertThat(checkoutResponse).isNotNull();
            soft.assertThat(checkoutResponse).validateJsonSchema("json_schema/checkout_validation_error.json");
        });
    }

    @Test
    
    @DisplayName("[/api/internal/v2/checkout][POST] Успешное создание checkout партнёра Split")
    void getCheckoutWithIncorrectPartner() {
        var bodyParams = CheckoutWithPartnerRequest.builder()
                .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                .merchantServiceName(SHISHKA_MERCHANT_NAME)
                .bnplPartner(INCORRECT_PROVIDER)
                .build();

        var checkoutResponse = bnplApiCheckoutService.getValidationError(bodyParams);

        step("Проверка ответа", () -> {
            soft.assertThat(checkoutResponse).isNotNull();
            soft.assertThat(checkoutResponse).validateJsonSchema("json_schema/checkout_validation_error.json");
        });
    }

    @ParameterizedTest
    @MethodSource("uz.click.bnpl_api.models.CheckoutValidationRequestFactory#missedParamRequest")
    
    @DisplayName("[/api/internal/v2/checkout][POST] Ошибка при отправке запроса без обязательного поля")
    void getCheckoutWithoutRequiredParams(CheckoutWithPartnerRequest bodyParams) {

        var checkoutResponse = bnplApiCheckoutService.getValidationError(bodyParams);

        step("Проверка ответа", () -> {
            soft.assertThat(checkoutResponse).isNotNull();
            soft.assertThat(checkoutResponse).validateJsonSchema("json_schema/checkout_validation_error.json");
        });
    }
}
