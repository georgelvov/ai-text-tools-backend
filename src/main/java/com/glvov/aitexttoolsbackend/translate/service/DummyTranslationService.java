package com.glvov.aitexttoolsbackend.translate.service;

import com.glvov.aitexttoolsbackend.translate.dto.TranslationRequest;
import com.glvov.aitexttoolsbackend.translate.dto.TranslationResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dummy")
@Log4j2
public class DummyTranslationService implements TranslationService {

    @Override
    public TranslationResponse translate(TranslationRequest request) {
        log.debug("Starting translation for text of length: {} using model: {} to language: {}",
                request.getText().length(), request.getModel(), request.getTargetLanguage());

        log.info("Translate request:  https://translation.com");

        // Simulate the same logic as real implementation but return dummy response
        String responseText = "Dummy: " + request.getText();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new TranslationResponse(responseText, "Dummy language");
    }
}
