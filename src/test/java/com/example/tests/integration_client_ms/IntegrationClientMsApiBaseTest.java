package com.example.tests.integration_client_ms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import com.example.api.integration_client_ms.IntegrationApiMSRequestSpecification;
import com.example.api.integration_client_ms.constants.IntegrationClientMSData;
import com.example.tests.BaseTest;

@ActiveProfiles("dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationClientMsApiBaseTest extends BaseTest {
    @Autowired
    protected IntegrationClientMSData integrationClientMSData;

    @Autowired
    protected IntegrationApiMSRequestSpecification integrationApiMSRequestSpecification;

    @BeforeAll
    void setUpAuth() {
        integrationApiMSRequestSpecification.setAuth(integrationClientMSData.getIntegrationLogin(),
                integrationClientMSData.getIntegrationPassword());
    }
}
