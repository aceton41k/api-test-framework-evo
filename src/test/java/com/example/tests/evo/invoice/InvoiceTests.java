package com.example.tests.evo.invoice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.invoice.InvoiceListResponse;
import com.example.api.evo.models.payment.FriendHelpRequest;
import com.example.api.evo.services.invoice.InvoiceService;
import com.example.api.evo.services.payments.PaymentService;
import com.example.tests.evo.EvoBaseTest;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.ServiceData.SERVICE_ID_UMS;

public class InvoiceTests extends EvoBaseTest {
    @Autowired
    InvoiceService invoiceService;

    @Autowired
    PaymentService paymentService;

    String secPhoneNumber;
    Map<String, String> additionalHeaders;

    @BeforeEach
    void setUp() {
        secPhoneNumber = UserData.generateUniquePhoneNumber();
        additionalHeaders = operations.userRegister(secPhoneNumber).getEvoHeaders();
        step("Создание инвойса для пользователя " + secPhoneNumber, () -> {
            var parameters = FriendHelpRequest.Parameters.builder()
                    .amount("1001")
                    .phoneNum(secPhoneNumber)
                    .build();

            var params = FriendHelpRequest.Params.builder()
                    .serviceId(SERVICE_ID_UMS)
                    .phoneNumber(phoneNumber)
                    .parameters(parameters)
                    .build();

            paymentService.friendHelp(params, additionalHeaders);
        });
    }

    @AfterEach
    void tearDown() {
        dataBaseService.deleteUserRegistration(secPhoneNumber);
    }

    @Test
    
    @DisplayName("[invoice.list] Проверка получения списка инвойсов")
    void getInvoiceList() {
        step("Запрос на получение списка инвойсов", () -> {
            InvoiceListResponse response = invoiceService.getInvoiceList(headers);

            step("Проверка, что ответ содержит идентификатор первого инвойса", () -> assertThat(
                    response.getResult().getFirst().getInvoiceId()).isNotEqualTo(0));
        });
    }


}
