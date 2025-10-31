package com.example.tests.travel.my_agent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.tests.BaseTest;
import com.example.api.travel.services.TravelMyAgentService;

import static io.qameta.allure.Allure.step;
import static com.example.api.travel.models.my_agent.MyAgentFlightSearchRequest.defaultFlightSearchParams;
import static com.example.api.travel.models.my_agent.MyAgentFlightSearchResponse.SearchSegment.iataOf;


public class MyAgentFlightsSearchTest extends BaseTest {
    @Autowired private TravelMyAgentService myAgentService;

    @Test
    
        @DisplayName("Успешный поиск билетов для партнера My Agent")
    void successfulSearchFlightsTest() {
        var resp = step("Установка дефолтных параметров", () ->
                myAgentService.myAgentSearchService(defaultFlightSearchParams())
        );
        soft.assertThat(resp.getSuccess()).isTrue();

        step("Валидируем структуру и содержимое MyAgentFlightSearchResponse", () -> {
            soft.assertThat(resp.getSuccess()).as("success").isTrue();
            soft.assertThat(resp.getCode()).as("code").isZero();
            soft.assertThat(resp.getTime()).as("time").isNotNull();

            // Проверяем первый сегмент поиска
            var seg0 = resp.getData().getSearch().getSegments().getFirst();
            soft.assertThat(iataOf(seg0.getFrom())).as("from.iata").isEqualTo("TAS");
            soft.assertThat(iataOf(seg0.getTo())).as("to.iata").isEqualTo("MOW");
            soft.assertThat(seg0.getDate()).as("date").isNotBlank();

            // Проверяем список рейсов
            var flights = resp.getData().getFlights();
            soft.assertThat(flights).as("flights").isNotEmpty();

            soft.assertThat(flights)
                    .as("каждый рейс корректен")
                    .allSatisfy(flight -> {
                        soft.assertThat(flight.getId()).as("id").isNotBlank();
                        soft.assertThat(flight.getDuration()).as("duration").isNotNull();
                        soft.assertThat(flight.getSegmentsCount()).as("segments_count").isNotNull();
                        soft.assertThat(flight.getIsBaggage()).as("is_baggage").isNotNull();
                    });
        });

    }
}
