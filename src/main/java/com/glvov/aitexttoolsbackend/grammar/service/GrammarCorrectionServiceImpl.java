package com.glvov.aitexttoolsbackend.grammar.service;

import com.glvov.aitexttoolsbackend.core.GeminiClient;
import com.glvov.aitexttoolsbackend.core.prompt.PromptService;
import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionRequest;
import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionResponse;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Profile("!llm-simulated")
@RequiredArgsConstructor
@Log4j2
public class GrammarCorrectionServiceImpl implements GrammarCorrectionService {

    private final GeminiClient geminiClient;
    private final PromptService promptService;


    public GrammarCorrectionResponse correctGrammar(GrammarCorrectionRequest request) {
        log.info("Starting grammar correction for text of length: {}", request.text().length());

        String prompt = promptService.createGrammarCorrectionPrompt(request.text());

        GenerateContentResponse response = geminiClient.generateContent(request.model().getValue(), prompt);

        log.info("Successfully received response ({} chars) from Gemini API",
                Objects.requireNonNull(response.text()).length());

        var grammarCorrectionResponse = new GrammarCorrectionResponse(response.text());

        log.debug("GrammarCorrection response: {}", grammarCorrectionResponse);

        return grammarCorrectionResponse;
    }
}
