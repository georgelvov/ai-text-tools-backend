package com.glvov.aitexttoolsbackend.translate.controller;

import com.glvov.aitexttoolsbackend.translate.dto.TranslationRequest;
import com.glvov.aitexttoolsbackend.translate.dto.TranslationResponse;
import com.glvov.aitexttoolsbackend.translate.service.TranslationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/translate")
@CrossOrigin(origins = "*")
@Log4j2
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TranslationResponse translate(@Valid @RequestBody TranslationRequest request) {
        log.info("Received translation request");
        return translationService.translate(request);
    }
} 