package com.example.asserts;

import org.assertj.core.api.AbstractAssert;
import com.example.api.evo.constants.ErrorCodes;
import com.example.api.evo.models.BaseErrorResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseErrorResponseAssert extends AbstractAssert<BaseErrorResponseAssert, BaseErrorResponse> {

    public BaseErrorResponseAssert(BaseErrorResponse actual) {
        super(actual, BaseErrorResponseAssert.class);
    }

    /*Тут проверяется только наличие ошибки кода и сообщение, сам язык сообщение не проверяется*/
    public BaseErrorResponseAssert hasError(ErrorCodes expected) {
        isNotNull();

        assertThat(actual.getError().getCode())
                .as("Код ошибки должен быть %s", expected.name())
                .isEqualTo(expected.getCode());

        String actualMessage = actual.getError().getMessage();
        String matchedLang = switch (actualMessage) {
            case String m when m.equals(expected.getMessageRu()) -> "ru";
            case String m when m.equals(expected.getMessageEn()) -> "en";
            case String m when m.equals(expected.getMessageUz()) -> "uz";
            default -> throw new IllegalStateException("Unexpected value: " + actualMessage);
        };

        assertThat(matchedLang)
                .as("Сообщение ошибки не соответствует ни одному из локализованных вариантов для %s.\nПолучено: \"%s\"",
                        expected.name(),
                        actualMessage)
                .isNotNull();

        return this;
    }

}
