package com.glvov.aitexttoolsbackend.functional.service;

import com.glvov.aitexttoolsbackend.dto.TextModificationRequest;
import com.glvov.aitexttoolsbackend.dto.TextModificationResponse;
import com.glvov.aitexttoolsbackend.dto.TranslationRequest;
import com.glvov.aitexttoolsbackend.dto.TranslationResponse;

public interface TextService {

    TextModificationResponse modifyText(TextModificationRequest request);

    TranslationResponse translateText(TranslationRequest request);
}
