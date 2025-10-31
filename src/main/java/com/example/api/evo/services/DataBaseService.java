package com.example.api.evo.services;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class DataBaseService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Step("Удаление пользователя из БД (Процедура identification.DELETE_USER_ACCOUNT)")
    public void deleteUserRegistration(@NonNull Long phoneNumber, @NonNull Long clientId) {
        log.info(
                "Удаление пользователя из БД. Процедура identification.DELETE_USER_ACCOUNT с параметрами: "
                        + "phoneNumber {}, clientId {})",
                phoneNumber,
                clientId);
        String storedProcedureCall = "{CALL [identification].[DELETE_USER_ACCOUNT](?, ?)}";
        try {
            jdbcTemplate.query(
                    connection -> {
                        CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);
                        callableStatement.setLong(1, phoneNumber);
                        callableStatement.setLong(2, clientId);
                        return callableStatement;
                    },
                    rs -> {
                        boolean hasResult = rs.next();
                        if (hasResult) {
                            int error = rs.getInt("error");
                            String errorNote = rs.getString("error_note");
                            log.info("Результат: error = {}, error_note = {}", error, errorNote);
                        } else {
                            log.warn("Процедура завершилась без возврата данных");
                        }
                        return null;
                    });
        } catch (DataAccessException e) {
            throw new RuntimeException("Ошибка выполнения хранимой процедуры %s"
                    .formatted(storedProcedureCall), e);
        }
    }

    public void deleteUserRegistration(String phoneNumber) {
            Long clientId = findClientId(Long.parseLong(phoneNumber));
            if (clientId != null){
                deleteUserRegistration(Long.parseLong(phoneNumber), clientId);
            }

    }

    @Step("Поиск clientId по номеру телефона")
    public Long findClientId(@NonNull Long phoneNumber) {
        String query = "SELECT id FROM dbo.CLICK_USERS WHERE default_phone_num = ?";
        log.info("Выполнение запроса '{}' с параметром {}", query, phoneNumber);

        try {
            List<Long> result = jdbcTemplate.query(query, (rs, rowNum) -> rs.getLong("id"), phoneNumber);

            if (result.isEmpty()) {
                log.warn("Клиент с номером телефона %d не найден".formatted(phoneNumber));
                return null;
            }

            return result.getFirst();

        } catch (DataAccessException e) {
            throw new RuntimeException("Ошибка выполнения операции '%s'".formatted(query), e);
        }
    }

    @Step("Remove restrictions from device")
    public void removeDeviceRestrictions(@NonNull String deviceImei) {
        String query = """
                 UPDATE CLICKDB1.evo.FRAUD_DEVICES_IMEI
                 SET status=0
                 WHERE imei = ?
                """;
        log.info("Выполнение запроса '{}' с параметром {}", query, deviceImei);
        try {
            jdbcTemplate.update(query, deviceImei);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Device with imei '%s' was not found".formatted(deviceImei), e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error during performing query '%s'".formatted(query), e);
        }

    }

    public Connection getConnection() throws SQLException {
        if (jdbcTemplate.getDataSource() == null) {
            throw new RuntimeException("Datasource was not initialized");
        }
        return jdbcTemplate.getDataSource().getConnection();
    }
}
