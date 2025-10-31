package com.example.tests.evo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import com.example.api.evo.constants.UserData;
import com.example.jupiter.annotations.SkipAuthSetup;
import com.example.tests.BaseTest;

import java.util.Map;

/**
 * Базовый класс для всех API-тестов Evolution.
 * Отвечает за автоматическую подготовку данных перед каждым тестом.
 */
public class EvoBaseTest extends BaseTest {

    protected Map<String, String> headers;
    protected String phoneNumber;

    @BeforeEach
    void baseSetUp(TestInfo testInfo) {
        if (skipRegistration(testInfo)) {
            return;
        }

        phoneNumber = UserData.generateUniquePhoneNumber();
        headers = operations.userRegister(phoneNumber).getEvoHeaders();
    }

    @AfterEach
    void baseTearDown(TestInfo testInfo) {
        if (skipRegistration(testInfo)) {
            return;
        }

        dataBaseService.deleteUserRegistration(phoneNumber);
    }

    private boolean skipRegistration(TestInfo info) {
        return info.getTestMethod().map(m -> m.isAnnotationPresent(SkipAuthSetup.class)).orElse(false)
                || info.getTestClass().map(c -> c.isAnnotationPresent(SkipAuthSetup.class)).orElse(false);
    }
}
