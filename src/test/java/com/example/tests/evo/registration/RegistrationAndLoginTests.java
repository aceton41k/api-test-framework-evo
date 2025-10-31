package com.example.tests.evo.registration;

import com.example.api.evo.models.registration.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.RegisterDeviceOperationResult;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.RegisterOperationResult;
import uz.click.evo_api.models.registration.*;
import com.example.api.evo.services.AuthenticationService;
import com.example.api.evo.services.DeviceService;
import com.example.jupiter.annotations.SkipAuthSetup;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationAndLoginTests extends EvoBaseTest {

    @Autowired
    DeviceService deviceService;

    @Autowired
    AuthenticationService authenticationService;

    String uuidRegexpUpperCase = "^[A-F0-9]{8}-[A-F0-9]{4}-[A-F0-9]{4}-[A-F0-9]{4}-[A-F0-9]{12}$",
            uuidRegexpLowerCase = "^[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$",
            sessionKeyRegexp = "^[a-f0-9]{30,}$",
            deviceIdRegExp = "^\\d{10}[A-F0-9]{20,}$";

    @Nested
    @SkipAuthSetup
    class DeviceRegisterTests extends EvoBaseTest {
        @Test
        
        @DisplayName("[device.register.request] Регистрация устройства")
        void deviceRegisterRequestTest() {
            step("Запрос на регистрацию", () -> {
                var params = DeviceRegisterRequest.Params.builder()
                        .phoneNumber(UserData.generateUniquePhoneNumber())
                        .build();
                DeviceRegisterResponse response = deviceService.deviceRegister(params);

                step("Проверка ответа", () ->
                        assertThat(response.getResult().getDeviceId())
                                .matches(deviceIdRegExp));
            });
        }
    }

    @Nested
    @SkipAuthSetup
    class DeviceRegisterConfirmTest extends EvoBaseTest {
        String phoneNumber = UserData.generateUniquePhoneNumber();
        String deviceId;

        @BeforeEach
        void setUp() {
            var params = DeviceRegisterRequest.Params.builder()
                    .phoneNumber(phoneNumber)
                    .build();
            deviceId = deviceService.deviceRegister(params).getResult().getDeviceId();
        }

        @Test
        
        @DisplayName("[device.register.confirm] Подтверждение устройства")
        void deviceRegisterConfirmTest() {
            step("Запрос на проверку девайса [device.register.confirm]", () -> {
                DeviceRegisterConfirmResponse response =
                        deviceService.confirmDevice(phoneNumber, DEFAULT_SMS, deviceId);

                step("Проверка ответа", () -> {
                    assertThat(response.getResult().getRegisterToken()).matches(uuidRegexpUpperCase);
                    assertThat(response.getResult().getClickpassToken()).matches(uuidRegexpUpperCase);
                });
            });
        }
    }

    @Nested
    @SkipAuthSetup
    class LoginTests extends EvoBaseTest {
        String phoneNumber = UserData.generateUniquePhoneNumber();
        String deviceId;

        @BeforeEach
        void setUp() {
            RegisterOperationResult result = operations.userRegister(phoneNumber);
            deviceId = result.getDeviceId();
        }

        @AfterEach
        void afterEach() {
            dataBaseService.deleteUserRegistration(phoneNumber);
        }

        @Test
        
        @DisplayName("[login] Вход")
        void loginTest() {
            step("Запрос на логин [login]", () -> {
                LoginResponse response = authenticationService.login(
                        deviceId, phoneNumber, DEFAULT_SMS, DEFAULT_PIN);

                step("Проверка ответа что ключ сессии не пустой", () -> {
                    soft.assertThat(response.getResult().getSessionKey()).as("session_key").matches(sessionKeyRegexp);
                    soft.assertThat(response.getResult().getWebSession()).as("web_session").matches(uuidRegexpLowerCase);
                    soft.assertThat(response.getResult().getIsIdentified()).as("is_identified").isFalse();
                    soft.assertThat(response.getResult().getFraudStatus()).as("fraud_status").isFalse();
                    soft.assertThat(response.getResult().getMonitoring().getEnabled()).as("monitoring.enabled").isTrue();
                    soft.assertThat(response.getResult().getMonitoring().getAmount()).as("monitoring.amount").isEqualTo(2000);
                    soft.assertThat(response.getResult().getUser().getName()).as("user.name").isNotNull();
                    soft.assertThat(response.getResult().getUser().getStatus()).as("user.status").isEqualTo("light");
                    soft.assertThat(response.getResult().getUser().getWalletOpened()).as("user.wallet_opened").isFalse();
                    soft.assertThat(response.getResult().getUser().getClientId()).as("user.client_id").isNotEqualTo(0);
                    soft.assertThat(response.getResult().getUser().getLanguage()).as("user.language").isNotNull();
                });
            });
        }
    }

    @Nested
    @SkipAuthSetup
    class RegistrationTests extends EvoBaseTest {
        String phoneNumber = UserData.generateUniquePhoneNumber();
        String deviceId;
        String registerToken;

        @BeforeEach
        void setUp() {
            RegisterDeviceOperationResult result = operations.registerDevice(phoneNumber, DEFAULT_SMS, DEFAULT_IMEI);
            deviceId = result.getDeviceId();
            registerToken = result.getRegisterToken();
        }

        @AfterEach
        void tearDown() {
            dataBaseService.deleteUserRegistration(phoneNumber);
        }

        @Test
        
        @DisplayName("[user.register] Регистрация пользователя")
        void userRegisterTest() {
            var regParams = RegisterRequest.Params.createDefault(
                    phoneNumber, registerToken, deviceId, DEFAULT_PIN);

            RegisterResponse response = step("Запрос на регистрацию [user.register]", () ->
                    authenticationService.register(regParams));

            step("Проверка тела ответа", () -> {
                soft.assertThat(response.getResult().getSessionKey()).as("session_key").matches(sessionKeyRegexp);
                soft.assertThat(response.getId()).as("response id").isNotBlank().isNotEqualTo("0");
                soft.assertThat(response.getResult().getWebSession()).as("web_session").matches(uuidRegexpLowerCase);
                soft.assertThat(response.getResult().getIsIdentified()).as("is_identified").isFalse();
                soft.assertThat(response.getResult().getMonitoring().getEnabled()).as("monitoring.enabled").isTrue();
                soft.assertThat(response.getResult().getMonitoring().getAmount()).as("monitoring.amount").isEqualTo(1000);
                soft.assertThat(response.getResult().getUser().getName()).as("user.name").isNotNull();
                soft.assertThat(response.getResult().getUser().getStatus()).as("user.status").isEqualTo("light");
                soft.assertThat(response.getResult().getUser().getWalletOpened()).as("user.wallet_opened").isFalse();
                soft.assertThat(response.getResult().getUser().getClientId()).as("user.client_id").isNotEqualTo(0);
                soft.assertThat(response.getResult().getUser().getLanguage()).as("user.language").isEqualTo("RU");
            });
        }
    }
}
