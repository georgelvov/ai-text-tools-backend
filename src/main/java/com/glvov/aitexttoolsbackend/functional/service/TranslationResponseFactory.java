package com.glvov.aitexttoolsbackend.functional.service;

import com.glvov.aitexttoolsbackend.dto.TranslationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class TranslationResponseFactory {

    private static final String UNKNOWN_LANGUAGE = "Unknown";


    public TranslationResponse create(String responseText) {
        if (!StringUtils.hasText(responseText)) {
            log.warn("Received empty response text from API");
            return new TranslationResponse("", UNKNOWN_LANGUAGE);
        }

        String trimmedResponse = responseText.trim();

        if (trimmedResponse.contains("|")) {
            return parseResponse(trimmedResponse);
        } else {
            log.debug("Response does not contain separator, treating as direct translation");
            return new TranslationResponse(trimmedResponse, UNKNOWN_LANGUAGE);
        }
    }

    private TranslationResponse parseResponse(String responseText) {
        String[] parts = responseText.split("\\|", 2);

        if (parts.length != 2) {
            log.warn("Invalid response format: expected 2 parts separated by '|', got {}", parts.length);
            return new TranslationResponse(responseText, UNKNOWN_LANGUAGE);
        }

        String detectedLanguage = parts[0].trim();
        String translatedText = parts[1].trim();

        if (!StringUtils.hasText(detectedLanguage)) {
            detectedLanguage = UNKNOWN_LANGUAGE;
        }

        return new TranslationResponse(detectedLanguage, translatedText);
    }
}
