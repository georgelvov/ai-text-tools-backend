package com.glvov.aitexttoolsbackend.translate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslationRequest {
    
    @NotBlank(message = "Text cannot be empty")
    @Size(max = 5000, message = "Text cannot exceed 5000 characters")
    private String text;

    @NotBlank(message = "Model must be selected")
    private String model;
    
    @NotBlank(message = "Target language must be selected")
    private String targetLanguage;
} 