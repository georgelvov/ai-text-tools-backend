package com.glvov.aitexttoolsbackend.grammar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrammarCorrectionRequest {
    
    @NotBlank(message = "Текст не может быть пустым")
    @Size(max = 1500, message = "Текст не может превышать 5000 символов")
    private String text;

    @NotBlank(message = "Модель должна быть выбрана")
    //@Pattern(regexp = "^(gemini-pro|gemma-7b)$", message = "Недопустимая модель")
    private String model;
} 