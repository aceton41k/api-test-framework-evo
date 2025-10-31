package com.example.tests.gosuslugi;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.gosuslugi.services.GovOperations;
import com.example.tests.BaseTest;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GovApiBaseTest extends BaseTest {

    @Autowired
    GovOperations govOperations;
    String webSession;
    Map<String, String> token;
}