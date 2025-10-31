package com.example.api.merchant.constants;

import lombok.Getter;
import org.opentest4j.TestAbortedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MerchantApiTestData {

    public static final String MERCHANT_USER_ID = "52192",
        MERCHANT_TRANS_ID = "36025";
    public static final int SERVICE_ID = 67479;
    public static final float VALID_AMOUNT = 123.45F;


    private static final String MESSAGE_MERCHANT_SECRET_KEY =
        "Environment variable 'MERCHANT_SECRET_KEY' is not set. Contact the responsible QA for the actual value";

    @Autowired
    private Environment env;

    @SuppressWarnings("CallToPrintStackTrace")
    public String getMerchantSecretKey() {
        String merchantKey = env.getProperty("MERCHANT_SECRET_KEY");
        if (merchantKey == null) {
            var e = new TestAbortedException(MESSAGE_MERCHANT_SECRET_KEY);
            e.printStackTrace();
            throw e;
        }
        return merchantKey;
    }
}
