package com.glvov.aitexttoolsbackend.functional.service;

import com.glvov.aitexttoolsbackend.dto.TextModificationRequest;
import com.glvov.aitexttoolsbackend.dto.TextModificationResponse;
import com.glvov.aitexttoolsbackend.dto.TranslationRequest;
import com.glvov.aitexttoolsbackend.dto.TranslationResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Profile("llm-simulated")
@Slf4j
public class TextServiceSimulator implements TextService {

    @Override
    @SneakyThrows
    public TextModificationResponse modifyText(TextModificationRequest request) {
        log.info("Text modification simulation. Type: {}, model: {}", request.type(), request.model());
        log.debug("Text to modify: {}", request.text());

        Thread.sleep(2000);

        return new TextModificationResponse(
                format("Dummy response for %s: %s", request.type().getValue(), request.text())
        );
    }

    @Override
    @SneakyThrows
    public TranslationResponse translateText(TranslationRequest request) {
        log.info("Translation simulation. Target language: {}, model: {}", request.targetLanguage(), request.model());
        log.debug("Text to translate: {}", request.text());

        Thread.sleep(2000);

        return new TranslationResponse(
                "Dummy language",
                format("Target lang: %s / Dummy translate: %s", request.targetLanguage(), request.text())
        );
    }
}
