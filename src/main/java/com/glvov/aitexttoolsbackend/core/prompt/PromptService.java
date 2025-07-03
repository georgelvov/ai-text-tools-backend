package com.glvov.aitexttoolsbackend.core.prompt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromptService {

    private final PromptLoader promptLoader;


    public String createGrammarCorrectionPrompt(String text) {
        return promptLoader.loadPrompt("grammar-correction", text);
    }

    public String createTranslationPrompt(String text, String targetLanguage) {
        return promptLoader.loadPrompt("translation", text, targetLanguage);
    }
}
