package com.example.api.bnpl.models;

import java.util.stream.Stream;

import static com.example.api.bnpl.constants.BnplConstants.*;

public class CheckoutValidationRequestFactory {

    public static Stream<CheckoutWithPartnerRequest> missedParamRequest() {
        return Stream.of(
                CheckoutWithPartnerRequest.builder()
                        .merchantServiceId(null)
                        .merchantServiceName(SHISHKA_MERCHANT_NAME)
                        .bnplPartner(SPLIT_PROVIDER)
                        .build(),

                CheckoutWithPartnerRequest.builder()
                        .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                        .merchantServiceName(null)
                        .bnplPartner(SPLIT_PROVIDER)
                        .build(),

                CheckoutWithPartnerRequest.builder()
                        .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                        .merchantServiceName(SHISHKA_MERCHANT_NAME)
                        .basket(null)
                        .bnplPartner(SPLIT_PROVIDER)
                        .build(),

                CheckoutWithPartnerRequest.builder()
                        .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                        .merchantServiceName(SHISHKA_MERCHANT_NAME)
                        .geo(null)
                        .bnplPartner(SPLIT_PROVIDER)
                        .build(),

                CheckoutWithPartnerRequest.builder()
                        .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                        .merchantServiceName(SHISHKA_MERCHANT_NAME)
                        .clientId(null)
                        .bnplPartner(SPLIT_PROVIDER)
                        .build(),

                CheckoutWithPartnerRequest.builder()
                        .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                        .merchantServiceName(SHISHKA_MERCHANT_NAME)
                        .totalAmount(null)
                        .bnplPartner(SPLIT_PROVIDER)
                        .build(),

                CheckoutWithPartnerRequest.builder()
                        .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                        .merchantServiceName(SHISHKA_MERCHANT_NAME)
                        .transactionType(null)
                        .bnplPartner(SPLIT_PROVIDER)
                        .build(),

                CheckoutWithPartnerRequest.builder()
                        .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                        .merchantServiceName(SHISHKA_MERCHANT_NAME)
                        .deviceId(null)
                        .bnplPartner(SPLIT_PROVIDER)
                        .build(),

                CheckoutWithPartnerRequest.builder()
                        .merchantServiceId(SHISHKA_LOUNGE_MERCHANT_ID)
                        .merchantServiceName(SHISHKA_MERCHANT_NAME)
                        .bnplPartner(null)
                        .build()
        );
    }
}
