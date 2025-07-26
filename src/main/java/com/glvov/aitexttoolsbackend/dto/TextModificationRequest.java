package com.glvov.aitexttoolsbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TextModificationRequest(
        @NotNull
        AiModel model,
        @NotNull
        TextModificationType type,
        @NotBlank
        @Size(max = 1500)
        String text) {
}