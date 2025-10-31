package com.example.tests.egov;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.egov.EGovRequestSpecification;
import com.example.api.egov.constants.EGovData;
import com.example.tests.BaseTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EGovApiBaseTest extends BaseTest {
    @Autowired
    protected EGovData eGovData;

    @Autowired
    protected EGovRequestSpecification eGovRequestSpecification;

    @BeforeAll
    void setUpAuth() {
        eGovRequestSpecification.setAuth(eGovData.getEGovLogin(), eGovData.getEGovPassword());
    }
}
