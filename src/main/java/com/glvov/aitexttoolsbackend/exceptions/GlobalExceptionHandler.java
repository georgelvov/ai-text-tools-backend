package com.glvov.aitexttoolsbackend.exceptions;

import com.glvov.aitexttoolsbackend.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(Exception e, HttpServletRequest request) {
        log.error(e, e);

        return new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                request.getRequestURI()
        );
    }
}
