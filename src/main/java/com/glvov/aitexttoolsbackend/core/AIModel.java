package com.glvov.aitexttoolsbackend.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AIModel {
    GEMINI_PRO("gemini-2.5-flash"),
    GEMMA_7B("gemma-3-27b-it");

    private final String value;

    public static AIModel from(String value) {
        for (AIModel model : values()) {
            if (model.getValue().equals(value)) {
                return model;
            }
        }
        throw new IllegalArgumentException("Неизвестная модель: " + value);
    }
} 