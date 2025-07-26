package com.glvov.aitexttoolsbackend.functional.prompt;

import com.glvov.aitexttoolsbackend.dto.TextModificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromptService {

    private final PromptNameRegistry promptNameRegistry;
    private final PromptLoader promptLoader;


    public String createTextModificationPrompt(String text, TextModificationType modificationType) {
        String promptName = promptNameRegistry.getTextModificationPromptName(modificationType);
        return promptLoader.loadPrompt(promptName, text);
    }

    public String createTranslationPrompt(String text, String targetLanguage) {
        String promptName = promptNameRegistry.getTranslationPromptName();
        return promptLoader.loadPrompt(promptName, text, targetLanguage);
    }
}
