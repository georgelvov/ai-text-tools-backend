package com.glvov.aitexttoolsbackend.grammar.service;

import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionRequest;
import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionResponse;

public interface GrammarCorrectionService {
    GrammarCorrectionResponse correctGrammar(GrammarCorrectionRequest request);
}
