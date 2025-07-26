package com.glvov.aitexttoolsbackend.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TextModificationType {

    FIX("fix"),
    SHORTEN("shorten"),
    ENHANCE("enhance"),
    FORMAL("formal"),
    CASUAL("casual"),
    REPHRASE("rephrase");

    @JsonValue
    private final String value;
}
