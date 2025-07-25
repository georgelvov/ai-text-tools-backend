package com.glvov.aitexttoolsbackend.grammar.controller;

import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionRequest;
import com.glvov.aitexttoolsbackend.grammar.dto.GrammarCorrectionResponse;
import com.glvov.aitexttoolsbackend.grammar.service.GrammarCorrectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grammar")
@Log4j2
@RequiredArgsConstructor
public class GrammarController {

    private final GrammarCorrectionService grammarCorrectionService;


    @PostMapping("/correct")
    @ResponseStatus(HttpStatus.OK)
    public GrammarCorrectionResponse correctGrammar(@Valid @RequestBody GrammarCorrectionRequest request) {
        log.info("Received grammar correction request");
        log.debug("Grammar correction request: {}", request);
        return grammarCorrectionService.correctGrammar(request);
    }
}
