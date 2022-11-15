package com.vitdevelop.todo_app.core.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceErrorCode {
    TODO_NOT_FOUND(1, "Todo not found");

    private final Integer code;
    private final String message;
}
