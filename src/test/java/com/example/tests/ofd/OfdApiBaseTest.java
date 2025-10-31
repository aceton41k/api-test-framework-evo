package com.example.tests.ofd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.example.api.ofd.OfdRequestSpecification;
import com.example.api.ofd.constants.OfdData;
import com.example.tests.BaseTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OfdApiBaseTest extends BaseTest {
    @Autowired
    protected OfdData ofdData;

    @Autowired
    protected OfdRequestSpecification ofdReqSpec;

    @Value("${OFD_LOGIN}")
    private String ofdLogin;

    @Value("${OFD_PASSWORD}")
    private String ofdPassword;


    @BeforeAll
    void setUpAuth() {
        if (ofdLogin.isBlank() || ofdPassword.isBlank()) {
            throw new IllegalStateException("OFD_LOGIN / OFD_PASSWORD не подтянулись из Vault.");
        }
        ofdReqSpec.setAuth(ofdLogin, ofdPassword);
    }
}
