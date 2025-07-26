package com.glvov.aitexttoolsbackend.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonPropertyOrder({"timestamp", "status", "error", "message", "path"})
public record ApiError(LocalDateTime timestamp,
                       int status,
                       String error,
                       String message,
                       String path) {

    public ApiError(HttpStatus status, String errorMessage, String path) {
        this(LocalDateTime.now(), status.value(), status.getReasonPhrase(), errorMessage, path);
    }
}
