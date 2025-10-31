package com.example.asserts;

import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Специализированный AssertJ-класс для проверки,
 * что заданный URL отвечает HTTP 200 OK на HEAD-запрос.
 *
 * <p>Пример использования:
 * <pre>{@code
 *     UrlAssert.assertThat("https://site.com")
 *              .isReachable();
 * }</pre>
 *
 * <p>Если сервер вернёт код, отличный от 200,
 * или URL будет недоступен / некорректен,
 * assertion завершится ошибкой {@link AssertionError}.
 */
public class UrlAssert extends AbstractAssert<UrlAssert, String> {
    /**
     * Создаёт новый {@code UrlAssert}.
     *
     * @param actual проверяемый URL (может быть как HTTP, так и HTTPS)
     */
    public UrlAssert(String actual) {
        super(actual, UrlAssert.class);
    }

    /**
     * Проверяет, что HEAD-запрос к {@code actual} возвращает HTTP 200 OK.
     * <p>В случае недоступности хоста, таймаута или любого статуса ≠ 200
     * будет выброшен {@link AssertionError}.
     */
    public void isReachable() {
        Objects.requireNonNull(actual);
        assertThatCode(() -> given().relaxedHTTPSValidation().when().head(actual).then().statusCode(200))
                .as(info.descriptionText() + " [%s]", actual)
                .doesNotThrowAnyException();
    }
}
