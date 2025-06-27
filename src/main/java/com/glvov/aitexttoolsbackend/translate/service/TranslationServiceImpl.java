package com.glvov.aitexttoolsbackend.translate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glvov.aitexttoolsbackend.core.AIModel;
import com.glvov.aitexttoolsbackend.core.config.GeminiConfig;
import com.glvov.aitexttoolsbackend.core.model.response.GeminiResponse;
import com.glvov.aitexttoolsbackend.grammar.service.GeminiRequestFactory;
import com.glvov.aitexttoolsbackend.translate.dto.TranslationRequest;
import com.glvov.aitexttoolsbackend.translate.dto.TranslationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Profile("default")
@RequiredArgsConstructor
@Log4j2
public class TranslationServiceImpl implements TranslationService {

    private final WebClient webClient;
    private final GeminiConfig geminiConfig;
    private final GeminiRequestFactory geminiRequestFactory;
    private final ObjectMapper objectMapper;


    public TranslationResponse translate(TranslationRequest request) {
        log.info("Starting translation for text of length: {} using model: {} to language: {}",
                request.getText().length(), request.getModel(), request.getTargetLanguage());

        String rawJson = webClient.post()
                .uri(geminiConfig.getApiUrl(AIModel.from(request.getModel())))
                .bodyValue(geminiRequestFactory.createTranslationRequest(request.getText(), request.getTargetLanguage()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("Raw JSON response from Gemini API: {}", rawJson);

        GeminiResponse response;

        try {
            response = objectMapper.readValue(rawJson, GeminiResponse.class);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing Gemini API response: {}", e.getMessage(), e);
            throw new RuntimeException("Error deserializing Gemini API response", e);
        }

        if (response == null || response.getFirstResponseText() == null) {
            throw new RuntimeException("Empty response from Gemini API");
        }

        String responseText = response.getFirstResponseText().trim();

        // Extract language and translation from the response
        String detectedLanguage;
        String translatedText;

        if (responseText.contains("|")) {
            String[] parts = responseText.split("\\|", 2);
            detectedLanguage = parts[0].trim();
            translatedText = parts[1].trim();
        } else {
            // If response doesn't contain the separator, assume it's a direct translation
            detectedLanguage = "Unknown";
            translatedText = responseText;
        }

        return new TranslationResponse(translatedText, detectedLanguage);
    }
} 