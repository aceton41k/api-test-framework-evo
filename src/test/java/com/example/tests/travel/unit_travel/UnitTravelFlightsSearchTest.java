package com.example.tests.travel.unit_travel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.tests.BaseTest;
import com.example.api.travel.services.UnitTravelService;

import static io.qameta.allure.Allure.step;
import static com.example.api.travel.models.unit_travel.UnitTravelFlightSearchRequest.defaultFlightSearchParams;

public class UnitTravelFlightsSearchTest extends BaseTest {

    @Autowired
    private UnitTravelService unitService;

    @Test
    
        @DisplayName("Успешный поиск билетов для партнера Unit Travel")
    void successfulSearchFlightsTest() {
        var resp = step("Установка дефолтных параметров", () ->
                unitService.unitTravelSearchService(defaultFlightSearchParams())
        );

        step("Валидируем структуру и содержимое UnitTravelFlightsSearchTest", () -> {
            soft.assertThat(resp).as("ответ сервиса").isNotEmpty();

            soft.assertThat(resp)
                    .as("каждый рейс корректен")
                    .allSatisfy(flight -> {
                        soft.assertThat(flight.getFlightId()).as("flightId").isNotBlank();
                        soft.assertThat(flight.getTotalPrice()).as("totalPrice").isNotNull();
                        soft.assertThat(flight.getSegments()).as("segments").isNotEmpty();

                        // Проверяем каждый сегмент внутри рейса
                        soft.assertThat(flight.getSegments())
                                .as("каждый сегмент корректен")
                                .allSatisfy(segment -> {
                                    soft.assertThat(segment).as("segment legs").isNotEmpty();

                                    // Проверяем каждый leg внутри сегмента
                                    soft.assertThat(segment)
                                            .as("каждый leg корректен")
                                            .allSatisfy(leg -> {
                                                soft.assertThat(leg.getDepAirpIATA()).as("DepAirpIATA").isEqualTo("TAS");
                                                soft.assertThat(leg.getArrAirpIATA()).as("ArrAirpIATA").isEqualTo("BHK");
                                                soft.assertThat(leg.getDepDateTime()).as("DepDateTime").startsWith("2026-01-20");
                                            });
                                });
                    });
        });

    }
}
