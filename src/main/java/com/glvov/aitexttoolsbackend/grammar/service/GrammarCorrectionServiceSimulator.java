package com.glvov.aitexttoolsbackend.grammar.service;

import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionRequest;
import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("llm-simulated")
@Slf4j
public class GrammarCorrectionServiceSimulator implements GrammarCorrectionService {

    @Override
    @SneakyThrows
    public GrammarCorrectionResponse correctGrammar(GrammarCorrectionRequest request) {
        log.info("Grammar correction request to: https://some-llm-api.com");
        Thread.sleep(3000);
        return new GrammarCorrectionResponse("Dummy response: " + request.text());
    }
}
