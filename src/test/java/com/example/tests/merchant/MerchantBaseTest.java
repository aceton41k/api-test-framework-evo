package com.example.tests.merchant;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.merchant.constants.MerchantApiTestData;
import com.example.tests.BaseTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class MerchantBaseTest extends BaseTest {

    @Autowired
    public MerchantApiTestData merchantApiTestData;
}
