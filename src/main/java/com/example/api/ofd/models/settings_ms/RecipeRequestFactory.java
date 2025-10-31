package com.example.api.ofd.models.settings_ms;

import java.util.List;

import static com.example.api.ofd.constants.OfdData.*;

public class RecipeRequestFactory {

    public static RecipeRequest createDefaultRecipeRequest() {
        return buildRecipeRequest(
                1,
                2,
                defaultConditions(),
                createDefaultPaymentInfo()
        );
    }

    public static RecipeRequest createUpdateRecipeRequest() {
        return buildRecipeRequest(
                1,
                2,
                updateConditions(),
                createPaymentInfo()
        );
    }

    private static RecipeRequest buildRecipeRequest(int serviceId, int priority,
                                                    List<RecipeRequest.Condition> conditions,
                                                    RecipeRequest.PaymentInfo paymentInfo) {
        return RecipeRequest.builder()
                .serviceId(serviceId)
                .priority(priority)
                .conditions(conditions)
                .paymentInfo(paymentInfo)
                .build();
    }

    private static List<RecipeRequest.Condition> defaultConditions() {
        return List.of(RecipeRequest.Condition.builder()
                .sourceField(SOURCE_FIELD)
                .matching(MATCHING)
                .sourceValue(SOURCE_VALUE)
                .build());
    }

    private static List<RecipeRequest.Condition> updateConditions() {
        return List.of(RecipeRequest.Condition.builder()
                .sourceField("upd" + SOURCE_FIELD)
                .matching(MATCHING)
                .sourceValue(SOURCE_VALUE)
                .build());
    }

    private static RecipeRequest.PaymentInfo createDefaultPaymentInfo() {
        return RecipeRequest.PaymentInfo.builder()
                .main(createMainPaymentDetail(true))
                .commission(createCommissionPaymentDetail())
                .build();
    }

    private static RecipeRequest.PaymentInfo createPaymentInfo() {
        return RecipeRequest.PaymentInfo.builder()
                .main(createMainPaymentDetail(false))
                .commission(createCommissionPaymentDetail())
                .build();
    }

    private static RecipeRequest.PaymentInfo.PaymentDetail createMainPaymentDetail(boolean isDefault) {
        var commissionInfo = RecipeRequest.PaymentInfo.PaymentDetail.ReceiptInfoRequest.Item.CommissionInfo.builder()
                .tin(isDefault ? TIN : COMMISSION_TIN)
                .pinfl(isDefault ? PINFL : COMMISSION_PINFL)
                .build();

        var item = RecipeRequest.PaymentInfo.PaymentDetail.ReceiptInfoRequest.Item.builder()
                .name(MAIN_SERVICE_NAME)
                .vatPercent(VAT_PERCENT)
                .spic(SPIC)
                .packageCode(PACKAGE_CODE)
                .discount(DISCOUNT)
                .commissionInfo(commissionInfo)
                .build();

        var extraInfo = RecipeRequest.PaymentInfo.PaymentDetail.ReceiptInfoRequest.ExtraInfo.builder()
                .phoneNumber(PHONE_NUMBER)
                .build();

        var receiptInfoRequest = RecipeRequest.PaymentInfo.PaymentDetail.ReceiptInfoRequest.builder()
                .receiptType(1)
                .items(List.of(item))
                .extraInfo(extraInfo)
                .build();

        return RecipeRequest.PaymentInfo.PaymentDetail.builder()
                .serviceName(MAIN_SERVICE_NAME)
                .main(true)
                .paymentType(MAIN_PAYMENT_TYPE)
                .receiptInfoRequest(receiptInfoRequest)
                .build();
    }

    private static RecipeRequest.PaymentInfo.PaymentDetail createCommissionPaymentDetail() {
        var item = RecipeRequest.PaymentInfo.PaymentDetail.ReceiptInfoRequest.Item.builder()
                .name(COMMISSION_SERVICE_NAME)
                .vatPercent(COMMISSION_VAT_PERCENT)
                .spic(COMMISSION_SPIC)
                .packageCode(COMMISSION_PACKAGE_CODE)
                .commissionInfo(RecipeRequest.PaymentInfo.PaymentDetail.ReceiptInfoRequest.Item.CommissionInfo.builder()
                        .tin(COMMISSION_TIN)
                        .pinfl(COMMISSION_PINFL)
                        .build())
                .build();

        var extraInfo = RecipeRequest.PaymentInfo.PaymentDetail.ReceiptInfoRequest.ExtraInfo.builder()
                .phoneNumber(PHONE_NUMBER)
                .build();

        var receiptInfoRequest = RecipeRequest.PaymentInfo.PaymentDetail.ReceiptInfoRequest.builder()
                .receiptType(1)
                .items(List.of(item))
                .extraInfo(extraInfo)
                .build();

        return RecipeRequest.PaymentInfo.PaymentDetail.builder()
                .serviceName(COMMISSION_SERVICE_NAME)
                .paymentType(COMMISSION_PAYMENT_TYPE)
                .receiptInfoRequest(receiptInfoRequest)
                .build();
    }
}
