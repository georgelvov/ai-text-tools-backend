package com.glvov.aitexttoolsbackend.controller;

import com.glvov.aitexttoolsbackend.dto.TextModificationRequest;
import com.glvov.aitexttoolsbackend.dto.TextModificationResponse;
import com.glvov.aitexttoolsbackend.dto.TranslationRequest;
import com.glvov.aitexttoolsbackend.dto.TranslationResponse;
import com.glvov.aitexttoolsbackend.functional.service.TextService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/text")
@Log4j2
@RequiredArgsConstructor
public class TextController {

    private final TextService textService;


    @PostMapping("/modify")
    public TextModificationResponse modifyText(@Valid @RequestBody TextModificationRequest request) {
        log.info("Received text modification request");
        TextModificationResponse response = textService.modifyText(request);
        log.info("Text modification successful");
        return response;
    }

    @PostMapping("/translate")
    public TranslationResponse translateText(@Valid @RequestBody TranslationRequest request) {
        log.info("Received translation request");
        TranslationResponse response = textService.translateText(request);
        log.info("Text translation successful");
        return response;
    }
}
