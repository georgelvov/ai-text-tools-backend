package com.glvov.aitexttoolsbackend.functional.prompt;

import com.glvov.aitexttoolsbackend.dto.TextModificationType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PromptNameRegistry {

    private static final String TRANSLATION_PROMPT_NAME = "translate.txt";

    private static final Map<TextModificationType, String> TEXT_MODIFICATION_PROMPT_NAMES = Map.of(
            TextModificationType.FIX, "fix.txt",
            TextModificationType.SHORTEN, "shorten.txt",
            TextModificationType.ENHANCE, "enhance.txt",
            TextModificationType.FORMAL, "formalize.txt",
            TextModificationType.CASUAL, "casualize.txt",
            TextModificationType.REPHRASE, "rephrase.txt"
    );


    public String getTextModificationPromptName(TextModificationType modificationType) {
        if (TEXT_MODIFICATION_PROMPT_NAMES.containsKey(modificationType)) {
            return TEXT_MODIFICATION_PROMPT_NAMES.get(modificationType);
        } else {
            throw new IllegalArgumentException("Unknown modification type: " + modificationType);
        }
    }

    public String getTranslationPromptName() {
        return TRANSLATION_PROMPT_NAME;
    }
}
