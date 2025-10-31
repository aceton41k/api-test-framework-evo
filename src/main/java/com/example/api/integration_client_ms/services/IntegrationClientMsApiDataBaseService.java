package com.example.api.integration_client_ms.services;

import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class IntegrationClientMsApiDataBaseService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Получить статус карты по токену (card_num_hash).
     */
    private String getCardStatusByToken(String token) {
        String sql = "SELECT status FROM CLICKDB1.dbo.CLICK_USER_ACCOUNTS WHERE card_num_hash = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, token);
        if (rows.isEmpty()) {
            log.warn("В БД нет записи с card_num_hash = {}", token);
            return null;
        }
        Object status = rows.getFirst().get("status");
        String result = status == null ? "NULL" : status.toString();
        log.info("Статус карты [{}] = {}", token, result);
        return result;
    }

    /**
     * Ожидать смену статуса до ожидаемого, с логами на каждом шаге.
     */
    public String waitUntilCardStatusEquals(String token, String expectedStatus) {

        AtomicReference<String> lastSeen = new AtomicReference<>("неизвестно");

        try {
            Awaitility.await().atMost(Duration.ofSeconds(15)).pollInterval(Duration.ofSeconds(1)).ignoreExceptions()
                      .untilAsserted(() -> {
                          String current = getCardStatusByToken(token);
                          lastSeen.set(current);
                          log.debug("→ Проверка статуса [{}]: текущий={}, ожидаемый={}", token, current,
                                  expectedStatus);
                      });

            log.info("Статус токена [{}] успешно изменился на [{}]", token, expectedStatus);
        } catch (Exception e) {
            log.error("Таймаут ожидания статуса. Последний статус [{}] = {}", token, lastSeen.get());
            throw e;
        }

        return getCardStatusByToken(token);
    }
}
