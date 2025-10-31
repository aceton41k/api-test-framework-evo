package com.example.tests.evo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.click_pass.TimeSynchronizationRequest;
import com.example.api.evo.models.click_pass.TimeSynchronizationResponse;
import com.example.api.evo.services.click_pass.TimeSynchronizationService;
import com.example.jupiter.annotations.SkipAuthSetup;
import com.example.tests.BaseTest;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SkipAuthSetup
public class ClickPassTests extends BaseTest {
    @Autowired
    TimeSynchronizationService timeSynchronizationService;

    private static Stream<Object> invalidTimeStamps() {
        long tashkentNow = ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toInstant().toEpochMilli();

        long oneDay = Duration.ofDays(1).toMillis();
        long tenMinutes = Duration.ofMinutes(10).toMillis();

        return Stream.of(
                tashkentNow - oneDay,     // -1 день
                tashkentNow - tenMinutes, // -10 минут
                tashkentNow + tenMinutes, // +10 минут
                tashkentNow + oneDay      // +1 день
        );
    }

    @Test
    @DisplayName("[time.sync] Проверка синхронизации времени при актуальном client_time")
    
    void timeSyncWithValidClientTimeTest() {
        long clientTime = ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toInstant().toEpochMilli();

        step("Отправляем запрос на синхронизацию времени с актуальным client_time = " + clientTime, () -> {
            var params = TimeSynchronizationRequest.Params.builder()
                    .datetime(clientTime)
                    .build();

            TimeSynchronizationResponse response = timeSynchronizationService.timeSync(params);
            var result = response.getResult();

            long receivedClientTime = result.getClientTime();
            long receiveTime = result.getReceiveTime();

            step("Проверяем, что client_time совпадает с отправленным", () ->
                    assertThat(receivedClientTime).isEqualTo(clientTime)
            );

            long maxAllowedDrift = Duration.ofMinutes(5).toMillis(); // 5 минут

            step("Проверяем, что receive_time близок к client_time (±" + maxAllowedDrift + " мс)", () ->
                    assertThat(receiveTime)
                            .as("receive_time должен быть близок к client_time")
                            .isCloseTo(receivedClientTime, within(maxAllowedDrift))
            );
        });
    }

    @ParameterizedTest
    @MethodSource("invalidTimeStamps")
    @Tag("negative")
    
    @DisplayName("[time.sync] Проверка сильного рассинхрона client_time и server_time (receive_time)")
    void timeSyncOffsetFromServerTimeTest(Long offset) {
        step("Отправляем запрос на синхронизацию времени с client_time = " + offset, () -> {
            var params = TimeSynchronizationRequest.Params.builder()
                    .datetime(offset)
                    .build();

            TimeSynchronizationResponse response = timeSynchronizationService.timeSync(params);
            var result = response.getResult();

            long clientTime = result.getClientTime();
            long receiveTime = result.getReceiveTime();

            step("Проверяем, что client_time совпадает с отправленным", () ->
                    assertThat(clientTime).isEqualTo(offset)
            );

            long drift = Math.abs(receiveTime - clientTime);
            long maxAllowedDrift = Duration.ofMinutes(5).toMillis(); // 5 минут

            step("Проверяем, что drift между client_time и receive_time превышает " + maxAllowedDrift + " мс", () ->
                    assertThat(drift)
                            .as("drift должен быть больше допустимого, т.к. client_time некорректен")
                            .isGreaterThan(maxAllowedDrift)
            );
        });
    }

}
