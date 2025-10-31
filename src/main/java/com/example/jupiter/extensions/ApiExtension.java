package com.example.jupiter.extensions;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.opentest4j.TestAbortedException;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.api.evo.services.DataBaseService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ApiExtension implements TestExecutionExceptionHandler, BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        assumeDataBaseAvailable(context);
    }

    /*
     * Если тест падает по ошибке десериализации то он получает статус failed а не broken
     */
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        String message = throwable.getMessage();

        if (message != null && (
                message.contains("Cannot deserialize") ||
                        message.contains("no suitable constructor") ||
                        message.contains("Unrecognized field") ||
                        message.contains("Missing required creator property") ||
                        message.contains("Unexpected token")||
                        message.contains("Unexpected character") //504 ошибках такой текст
        )) {
            throw new AssertionError(
                    "Ошибка десериализации: ответ сервиса не соответствует ожидаемой модели. Проверь структуру JSON и класс ответа.",
                    throwable);
        }

        throw throwable;
    }


    /**
     * Check if Database is available. If it isn't – skip test.
     */
    @SuppressWarnings("CallToPrintStackTrace")
    private void assumeDataBaseAvailable(ExtensionContext context) {
        Optional<ExtensionContext> current = Optional.of(context);
        ExtensionContext ec = current.get();
        ApplicationContext appContext = SpringExtension.getApplicationContext(ec);
        DataBaseService dataBaseService = appContext.getBean(DataBaseService.class);
        try (Connection connection = dataBaseService.getConnection()) {
            if (connection.isClosed()) {
                throw new RuntimeException("Database connection is closed. Test will be skipped");
            }
        } catch (SQLException e) {
            var ex = new TestAbortedException("Database is unavailable now. Test will be skipped", e);
            ex.printStackTrace();
            throw ex;
        }
    }
}
