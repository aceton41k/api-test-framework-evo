package com.example.api.ussd.services;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class UssdDBService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Step("Удаление пользователя из БД")
    @Transactional
    public void deleteClient(@NonNull String phoneNumber){
        log.info("Удаление пользователя");
        deleteRow("DELETE FROM CLICKDB1.dbo.CLICK_USER_ACCOUNTS WHERE phone_num = ?", phoneNumber);
        deleteRow("DELETE FROM CLICKDB1.dbo.CLICK_USER_ACC_PHONES WHERE phone_num = ?", phoneNumber);
        deleteRow("DELETE FROM CLICKDB1.dbo.CLICK_USERS WHERE default_phone_num = ?", phoneNumber);
        deleteRow("DELETE FROM CLICKDB1.dbo.CLICK_USERS_PHONES WHERE phone_num = ?", phoneNumber);
        deleteRow("DELETE FROM CLICKDB1.dbo.USSD_GUEST_PHONES WHERE phone_num = ?", phoneNumber);
    }

    @Step("Смена языка пользователя")
    @Transactional
    public void changeLangClient(@NonNull String phoneNumber, @NonNull String language){
        log.info("Смена языка на {}", language);
        try {
            jdbcTemplate.update("UPDATE CLICKDB1.dbo.CLICK_USERS_PHONES SET lang = ? WHERE phone_num = ?", language, phoneNumber);
            log.info("Изменён язык для {}", phoneNumber);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при обновлении языка для пользователя: " + phoneNumber, e);
        }
    }

    @Step("Получить язык пользователя из БД")
    public String checkLanguage(@NonNull String phoneNumber){
        log.info("Запрос языка пользователя");
        try {
            return jdbcTemplate.queryForObject("SELECT lang FROM clickdb1.dbo.CLICK_USERS_PHONES WHERE phone_num = ?", String.class, phoneNumber);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при выполнении запроса", e);
        }
    }


    private void deleteRow(String sqlQuery, String param) {
        log.info("Удаление записи");
        try {
            jdbcTemplate.update(sqlQuery, param);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при удалении по запросу: " + sqlQuery, e);
        }
    }

}
