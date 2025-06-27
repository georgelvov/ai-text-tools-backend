package com.glvov.aitexttoolsbackend.grammar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrammarCorrectionResponse {
    private String correctedText;
} 