package com.glvov.aitexttoolsbackend.translate.service;

import com.glvov.aitexttoolsbackend.translate.dto.TranslationRequest;
import com.glvov.aitexttoolsbackend.translate.dto.TranslationResponse;

public interface TranslationService {

    TranslationResponse translate(TranslationRequest request);
}
