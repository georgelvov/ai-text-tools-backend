package com.glvov.aitexttoolsbackend.functional;

import com.glvov.aitexttoolsbackend.exceptions.GeminiApiException;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GeminiClient {

    private final Client genaiClient;


    public GenerateContentResponse generateContent(String model, String prompt) {
        log.debug("Sending request to Gemini API, model: {}", model);

        GenerateContentResponse response = genaiClient.models.generateContent(model, prompt, null);

        log.debug("Gemini API response: {}", response);

        if (response == null || response.text() == null) {
            throw new GeminiApiException("Empty response from Gemini API");
        }

        return response;
    }
}
