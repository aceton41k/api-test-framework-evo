package com.example.tests.evo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.tickets.AddTicketRequest;
import com.example.api.evo.models.tickets.GetTicketTypesResponse;
import com.example.api.evo.models.tickets.PaymentCancellationTicketAddRequest;
import com.example.api.evo.models.tickets.PaymentCancellationTicketTypesResponse;
import com.example.api.evo.services.tickets.TicketService;
import com.example.jupiter.annotations.SkipAuthSetup;
import com.example.utils.DateTimeUtil;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TicketTests extends EvoBaseTest {
    @Autowired
    TicketService ticketService;

    @Test
    
    @DisplayName("[ticket.get.types] Получить типы категорий")
    @SkipAuthSetup
    void getTicketTypes() {
        GetTicketTypesResponse response =
                step("[ticket.get.types] Получить типы категорий", () -> ticketService.getTicketTypes());
        step("Проверка ответа что имя категории не пустой", () -> {
            assertThat(response.getResult().getFirst().getName()).isNotBlank();
        });
    }

    @Test
    
    @DisplayName("[ticket.add] Добавить категорию")
    @Disabled("Пока не понятно почему падает отключу")
    void addTicket() {
        step("[ticket.add] Добавить категорию", () -> {
            AddTicketRequest.Params params = AddTicketRequest.Params.builder()
                    .type("REGISTRATION")
                    .clientName("client_name")
                    .description("description")
                    .build();
            ResponseWithOkResult response = ticketService.addTicket(params, headers);

            step("Проверка ответа что получили ответ ок", () -> {
                assertThat(response.getResult()).isEqualTo("ok");
            });
        });
    }

    @Test
    
    @DisplayName("[ticket.cancel.types] Список категорий для отмены платежа")
    void getPaymentCancellationTicketTypes() {
        PaymentCancellationTicketTypesResponse response = step(
                "[ticket.cancel.types] Список категорий для отмены платежа",
                () -> ticketService.getPaymentCancellationTicketTypes(headers));
        step("Проверка ответа", () -> {
            assertThat(response.getResult().getOptions().getFirst().getName()).isNotBlank();
        });
    }

    @Test
    
    @DisplayName("[ticket.cancel.add] Добавить заявку на отмену платежа")
    @Disabled("Пока не понятно почему падает отключу")
    void addTicketToCancelPayment() {
        step("[ticket.cancel.add] Добавить заявку на отмену платежа", () -> {
            PaymentCancellationTicketAddRequest.Params params = PaymentCancellationTicketAddRequest.Params.builder()
                    .type("TICKET_AMOUNT")
                    .clientName("Test")
                    .paymentId(DateTimeUtil.getCurrentTimestamp())
                    .build();
            ResponseWithOkResult response = ticketService.addPaymentCancellationTicket(params,
                    headers);

            step("Проверка ответа что получили ответ ок", () -> {
                assertThat(response.getResult()).isEqualTo("ok");
            });
        });
    }

    @Test
    
    @DisplayName("[ticket.callback.request] Запрос на звонок")
    @Disabled("Пока не понятно почему падает отключу")
    void requestCallback() {
        ResponseWithOkResult response =
                step("[ticket.callback.request] Запрос на звонок",
                        () -> ticketService.requestCallbackTicket(headers));
        step("Проверка ответа что получили ответ ок", () -> {
            assertThat(response.getResult()).isEqualTo("ok");
        });
    }
}
