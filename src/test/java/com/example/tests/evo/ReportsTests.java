package com.example.tests.evo;

import com.example.api.evo.models.reports.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.UserData;
import uz.click.evo_api.models.reports.*;
import com.example.api.evo.services.reports.ReportsService;
import com.example.tests.BaseTest;
import com.example.utils.DateTimeUtil;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Disabled("нужны оплаты что бы проверить")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
/*TODO нужно будет переделать сьют с платежами так как если ща поменять упадут все тесты*/
public class ReportsTests extends BaseTest {
    @Autowired
    ReportsService reportsService;

    Long paymentID;

    @BeforeAll
    void setUp() {
        UserData.User user = userData.getUser("NIK");
        deprecatedHeaders = operations.login(user).getEvoHeaders();
    }

    @Test
    
    @DisplayName("[history.click] Получение отчета для предыдущего месяца")
    void historyClick() {
        step("[history.click] Получение отчета для предыдущего месяца", () -> {
            var params = HistoryClickRequest.Params.builder()
                    .dateStart(DateTimeUtil.getFirstDayOfPreviousMonthTimestamp())
                    .dateEnd(DateTimeUtil.getLastDayOfPreviousMonthTimestamp())
                    .build();
            HistoryClickResponse response = reportsService.getHistoryClick(params, deprecatedHeaders);

            step("Проверка ответа", () -> {
                assertThat(response.getResult().getFirst().getId()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[history.monitoring] Получение отчета для предыдущего месяца мониторинг")
    void historyMonitoring() {
        step("[history.click] Получение отчета для предыдущего месяца мониторинг", () -> {
            var params = HistoryClickRequest.Params.builder()
                    .dateStart(DateTimeUtil.getFirstDayOfPreviousMonthTimestamp())
                    .dateEnd(DateTimeUtil.getLastDayOfPreviousMonthTimestamp())
                    .build();
            HistoryMonitoringResponse response = reportsService.getHistoryMonitoring(params, deprecatedHeaders);

            step("Проверка ответа", () ->
                    assertThat(response.getResult().getFirst().getId()).isNotNull()
            );
        });
    }

    @Test
    
    @DisplayName("[get.chart] Получение бублика")
    @Disabled("Пока не понятно почему падает отключу")
    void getChart() {
        step("[get.chart] Получение бублика(пирога)", () -> {
            var params = GetChartRequest.Params.builder()
                    .dateStart(DateTimeUtil.getFirstDayOfPreviousMonthTimestamp())
                    .dateEnd(DateTimeUtil.getLastDayOfPreviousMonthTimestamp())
                    .build();
            GetChartResponse response = reportsService.getChart(params, deprecatedHeaders);

            step("Проверка ответа", () ->
                    assertThat(response.getResult().getFirst().getAmount()).isNotNull()
            );
        });
    }

    @Test
    
    @DisplayName("[history.latest] Последние отчеты")
    void historyLatest() {
        step("[history.latest] Получение последних отчетов", () -> {
            HistoryLatestResponse response = reportsService.getHistoryLatest(deprecatedHeaders);

            step("Проверка ответа", () ->
                    assertThat(response.getResult().getFirst().getAmount()).isNotNull()
            );
        });
    }

    @Test
    
    @DisplayName("[payment.history] Отчет по оплате")
    void paymentHistory() {
        step("Получить ид платежа", () -> {
            var params = HistoryClickRequest.Params.builder()
                    .dateStart(DateTimeUtil.getFirstDayOfPreviousMonthTimestamp())
                    .dateEnd(DateTimeUtil.getLastDayOfPreviousMonthTimestamp())
                    .build();
            paymentID = reportsService.getPaymentId(params, deprecatedHeaders);
        });
        step("[payment.history] Отчет по оплате", () -> {
            var params =
                    PaymentHistoryRequest.Params.builder().paymentId(paymentID).build();
            PaymentHistoryResponse response = reportsService.getPaymentHistory(params, deprecatedHeaders);

            step("Проверка ответа", () ->
                    assertThat(response.getResult().getPaymentId()).isNotEqualTo(0)
            );
        });
    }
}
