package com.glvov.aitexttoolsbackend.translate.service;

import com.glvov.aitexttoolsbackend.core.GeminiClient;
import com.glvov.aitexttoolsbackend.core.prompt.PromptService;
import com.glvov.aitexttoolsbackend.translate.dto.TranslationRequest;
import com.glvov.aitexttoolsbackend.translate.dto.TranslationResponse;
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
public class TranslationServiceImpl implements TranslationService {

    private final GeminiClient geminiClient;
    private final PromptService promptService;
    private final TranslationResponseFactory translationResponseFactory;


    public TranslationResponse translate(TranslationRequest request) {
        log.info("Starting translation for text of length: {} to language: {}",
                request.text().length(), request.targetLanguage());

        String prompt = promptService.createTranslationPrompt(request.text(), request.targetLanguage());

        GenerateContentResponse contentResponse = geminiClient.generateContent(request.model().getValue(), prompt);

        log.info("Successfully received response ({} chars) from Gemini API",
                Objects.requireNonNull(contentResponse.text()).length());

        TranslationResponse translationResponse = translationResponseFactory.createResponse(contentResponse.text());

        log.debug("TranslationResponse : {}", translationResponse);

        return translationResponse;
    }
}
