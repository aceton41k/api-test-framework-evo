package com.example.api.ofd.constants;

import org.opentest4j.TestAbortedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class OfdData {

    @Autowired
    private Environment env;

    public String getOfdAdminLogin() {
        return getEnvValue("OFD_LOGIN");
    }

    public String getOfdAdminPassword() {
        return getEnvValue("OFD_PASSWORD");
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private String getEnvValue(String envName) {
        String msg = "Environment variable '%s' is not set. Contact responsible QA to get actual value".formatted(envName);
        String value = env.getProperty(envName);
        if (value == null) {
            var e = new TestAbortedException(msg);
            e.printStackTrace();
            throw e;
        }
        return value;
    }

    public static final int
            VAT_PERCENT = 7,
            DISCOUNT = 22,
            COMMISSION_VAT_PERCENT = 2,
            DEFAULT_PAGE_NUMBER = 0,
            DEFAULT_PAGE_SIZE = 10,
            PRICE = 1000,
            AMOUNT = 0,
            STATUS_CODE = 0,
            HAZELCASR_INFO_CODE = 1288863;
    public static final Long
            RECEIPT_ID = 711800053L,
            GOOD_PRICE = 240000L,
            TOTAL_VAT = 15699L,
            VAT = 15699L,
            RECEIVED_CASH = 0L,
            RECEIVED_CARD = 0L,
            RECEIVEDE_CASH = 0L,
            PAYMENT_ID = 465L;
    public static final Byte
            RECEIPT_TYPE = 1, // Для assert в ответе receipt-ms: getSelectedReceiptInfo
            IS_REFUND = 0, // Для assert в ответе receipt-ms: getSelectedReceiptInfo
            MESSAGE_TYPE = 5; // Для assert в ответе receipt-ms: getSelectedReceiptInfo
    public static final String SOURCE_FIELD = "cntrg_info_param5",
            SOURCE_VALUE = "90",
            MATCHING = "EQUALS",
            NOT_MATCHING = "NOT_EQUALS",
            MAIN_SERVICE_NAME = "tester store",
            MAIN_PAYMENT_TYPE = "PAYMENT_TYPE_1",
            COMMISSION_PAYMENT_TYPE = "PAYMENT_TYPE_1",
            SPIC = "02710001003000000",
            PACKAGE_CODE = "1282118",
            TIN = "309270925",
            PINFL = "31909090000090",
            PHONE_NUMBER = "998000001122",
            COMMISSION_SERVICE_NAME = "?? \"CLICK\"",
            COMMISSION_SPIC = "02710001003000000",
            COMMISSION_PINFL = "319090900000909",
            COMMISSION_PACKAGE_CODE = "1282118",
            COMMISSION_TIN = "309270924",
            DELETE_MXIK_CODE = "10306011001000001",
            UPDATE_MXIK_CODE = "10306011001000000",
            ACTIVE_MXIK_CODE = "02710001007000000",
            NEW_UPDATE_MXIK_CODE = "10305008004000000",
            PRODUCTS_NAME = "Test Item12",
            BARCODE = "123123123",
            QR_CODE_URL = "QR_CODE_URL",
            RECEIPT_QR_CODE_URL = "RECEIPT_QR_CODE_URL",
            OPERATION_DATETIME = "2025-05-30T17:44:40.18",
            TERMINAL_ID = "EP000000000354",
            NAME_UZ_CYRL = "АИ-80 маркали бензин",
            NAME_UZ_LATIN = "AI-80 markali benzin",
            NAME_RU = "Бензин марки АИ-80",
            USE_PACKAGE = "1",
            USE_LICENSE = "0";
}