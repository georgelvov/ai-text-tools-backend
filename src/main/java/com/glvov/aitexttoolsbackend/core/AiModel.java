package com.glvov.aitexttoolsbackend.core;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AiModel {

    GEMINI_PRO("gemini-2.5-flash"),
    GEMMA_7B("gemma-3-27b-it");

    @JsonValue
    private final String value;
}