package com.glvov.aitexttoolsbackend.translate.dto;

import com.glvov.aitexttoolsbackend.core.AiModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TranslationRequest(
        @NotNull
        AiModel model,
        @NotBlank
        @Size(max = 1500)
        String text,
        @NotBlank
        String targetLanguage) {
}
