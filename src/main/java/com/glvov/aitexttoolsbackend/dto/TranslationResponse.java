package com.glvov.aitexttoolsbackend.dto;

public record TranslationResponse(
        String detectedLanguage,
        String translatedText) {
}