package com.glvov.aitexttoolsbackend.grammar.service;

import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionRequest;
import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Profile("dummy")
public class DummyGrammarCorrectionService implements GrammarCorrectionService {
    
    @Override
    public GrammarCorrectionResponse correctGrammar(GrammarCorrectionRequest request) {
        log.info("Текст: {}", request.getText());

        log.info("Grammar Correctio request: https://grammar-correction.com");

        // Simulate the same logic as real implementation but return dummy response
        String correctedText = "Dummy: " + request.getText();

        log.debug("Dummy grammar correction response: {}", correctedText);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        return new GrammarCorrectionResponse(correctedText);
    }
}
