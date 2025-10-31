package com.example.api.ofd.models.merchant_ms;

import java.util.List;

import static com.example.api.ofd.constants.OfdData.*;


public class ProductsRequestFactory {
    public static ProductsRequest createDefault() {
        return ProductsRequest.builder()
                .paymentId(PAYMENT_ID)
                .receivedECash(RECEIVED_CASH)
                .receivedCard(RECEIVED_CARD)
                .receivedCash(RECEIVED_CASH)
                .items(defaultItems())
                .build();
    }

    private static List<ProductsRequest.Item> defaultItems() {
        return List.of(ProductsRequest.Item.builder()
                .name(PRODUCTS_NAME)
                .barcode(BARCODE)
                .spic(SPIC)
                .packageCode(PACKAGE_CODE)
                .price(PRICE)
                .vat(VAT)
                .vatPercent(VAT_PERCENT)
                .amount(AMOUNT)
                .commissionInfo(ProductsRequest.CommissionInfo.builder()
                        .tin(TIN)
                        .pinfl(PINFL)
                        .build())
                .build());
    }
}
