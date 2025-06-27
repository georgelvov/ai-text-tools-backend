package com.glvov.aitexttoolsbackend.grammar.service;

import com.glvov.aitexttoolsbackend.core.AIModel;
import com.glvov.aitexttoolsbackend.core.config.GeminiConfig;
import com.glvov.aitexttoolsbackend.core.model.response.GeminiResponse;
import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionRequest;
import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Profile("default")
@RequiredArgsConstructor
@Log4j2
public class GrammarCorrectionServiceImpl implements GrammarCorrectionService {

    private final WebClient webClient;
    private final GeminiConfig geminiConfig;
    private final GeminiRequestFactory geminiRequestFactory;


    public GrammarCorrectionResponse correctGrammar(GrammarCorrectionRequest request) {
        log.info("Начинаю исправление грамматики для текста длиной: {} используя модель: {}",
                request.getText().length(), request.getModel());

        GeminiResponse response = webClient.post()
                .uri(geminiConfig.getApiUrl(AIModel.from(request.getModel())))
                .bodyValue(geminiRequestFactory.createGeminiRequest(request.getText()))
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .block();

        if (response == null || response.getFirstResponseText() == null) {
            throw new RuntimeException("Пустой ответ от Gemini API");
        }

        return new GrammarCorrectionResponse(response.getFirstResponseText());
    }
}
