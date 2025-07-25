package com.glvov.aitexttoolsbackend.translate.service;

import com.glvov.aitexttoolsbackend.translate.dto.TranslationRequest;
import com.glvov.aitexttoolsbackend.translate.dto.TranslationResponse;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("llm-simulated")
@Log4j2
public class TranslationServiceSimulator implements TranslationService {

    @Override
    @SneakyThrows
    public TranslationResponse translate(TranslationRequest request) {
        log.info("Translation request for target language: '{}' to  https://some-llm-api.com", request.targetLanguage());
        Thread.sleep(3000);
        return new TranslationResponse(
                "Dummy language",
                "Target lang: " + request.targetLanguage() + " / Dummy translate: " + request.text()
        );
    }
}
