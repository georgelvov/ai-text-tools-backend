package com.glvov.aitexttoolsbackend.translate.dto;

public record TranslationResponse(String detectedLanguage,
                                  String translatedText) {
}