package com.vitdevelop.todo_app.core.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceErrorCode {
    TODO_NOT_FOUND(1, "Todo not found"),
    USER_NOT_FOUND(2, "User not found"),
    USER_ALREADY_EXISTS(3, "User already exists"),
    SUCCESFUL_ADDED(4,"Succesful added" );

    private final Integer code;
    private final String message;
}
