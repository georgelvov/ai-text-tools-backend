package com.glvov.aitexttoolsbackend.exceptions;

public class PromptLoadException extends RuntimeException {
    public PromptLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}