package com.example.api.evo.constants;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserData {
    public static final String DEFAULT_SMS = "DEFAULT_SMS";
    public static final String DEFAULT_PIN = "DEFAULT_PIN";
    public static final String DEFAULT_IMEI = "DEFAULT_IMEI";
    private final Map<String, User> users = Map.of(
            "ABC", new User("111111111111", "111111", "111111"),
            "DEF", new User("111111111111", "111111", "111111"),
            "GHI", new User("111111111111", "111111", "111111"));

    public User getUser(@NotNull String key) {
        return users.get(key);
    }

    @Getter
    public static class User {
        private final String phoneNumber;
        private final String smsCode;
        private final String pinCode;

        public User(String phoneNumber, String smsCode, String pinCode) {
            this.phoneNumber = phoneNumber;
            this.smsCode = smsCode;
            this.pinCode = pinCode;
        }
    }

    /**
     * Хранилище уже сгенерированных номеров.
     * Используется для исключения коллизий в рамках одного запуска.
     */
    private static final Set<String> usedNumbers = ConcurrentHashMap.newKeySet();

    /**
     * Генерирует уникальный номер телефона формата 99897XXXXXXX.
     * Обеспечивает отсутствие повторов путём хэширования UUID и сохранения использованных комбинаций.
     *
     * @return строка с уникальным номером телефона
     */
    public static String generateUniquePhoneNumber() {
        while (true) {
            int hash = UUID.randomUUID().toString().hashCode();
            String uniquePart = String.format("%07d", Math.abs(hash % 10_000_000));
            if (usedNumbers.add(uniquePart)) {
                return "99897" + uniquePart;
            }
        }
    }
}
