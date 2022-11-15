package com.vitdevelop.todo_app.core.exception;

import com.vitdevelop.todo_app.core.domain.enums.ServiceErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;

@Getter
public class ServiceException extends ResponseStatusException {
    @Serial
    private static final long serialVersionUID = -7265129017845673416L;

    private final ServiceErrorCode code;

    public ServiceException(ServiceErrorCode code) {
        super(HttpStatus.BAD_REQUEST, code.getMessage());
        this.code = code;
    }
}
