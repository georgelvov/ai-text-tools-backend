package com.glvov.aitexttoolsbackend.functional.service;

import com.glvov.aitexttoolsbackend.dto.*;
import com.glvov.aitexttoolsbackend.functional.GeminiClient;
import com.glvov.aitexttoolsbackend.functional.prompt.PromptService;
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
public class TextServiceImpl implements TextService {

    private final GeminiClient geminiClient;
    private final PromptService promptService;
    private final TranslationResponseFactory translationResponseFactory;


    @Override
    public TextModificationResponse modifyText(TextModificationRequest request) {
        log.info("Starting text modification. Type: {}, textLength: {}, model: {}",
                request.type(), request.text().length(), request.model().getValue());

        log.debug("Text to modify: {}", request.text());

        String prompt = promptService.createTextModificationPrompt(request.text(), request.type());

        log.debug("Prompt for text modification of type {}:\n{}", request.type(), prompt);

        GenerateContentResponse response = generateContent(request.model(), prompt);

        var textCorrectionResponse = new TextModificationResponse(response.text());

        log.debug("Text modification response: {}", textCorrectionResponse);

        return textCorrectionResponse;
    }

    @Override
    public TranslationResponse translateText(TranslationRequest request) {
        log.info("Starting translation text to language: {}. Text length: {}, Model: {}",
                request.targetLanguage(), request.text().length(), request.model());

        log.debug("Text to translate: {}", request.text());

        String prompt = promptService.createTranslationPrompt(request.text(), request.targetLanguage());

        GenerateContentResponse response = generateContent(request.model(), prompt);

        var translationResponse = translationResponseFactory.create(response.text());

        log.debug("Translation response: {}", translationResponse);

        return translationResponse;
    }


    private GenerateContentResponse generateContent(AiModel model, String prompt) {
        GenerateContentResponse contentResponse = geminiClient.generateContent(model.getValue(), prompt);

        log.info("Successfully received response ({} chars) from Gemini API",
                Objects.requireNonNull(contentResponse.text()).length());

        return contentResponse;
    }
}
