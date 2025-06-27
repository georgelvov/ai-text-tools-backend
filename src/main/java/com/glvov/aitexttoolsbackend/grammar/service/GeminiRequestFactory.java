package com.glvov.aitexttoolsbackend.grammar.service;

import com.glvov.aitexttoolsbackend.core.model.request.GeminiRequest;
import org.springframework.stereotype.Component;

@Component
public class GeminiRequestFactory {

    public GeminiRequest createGeminiRequest(String text) {
        String prompt = buildGrammarCorrectionPrompt(text);
        return GeminiRequest.of(prompt);
    }

    public GeminiRequest createTranslationRequest(String text, String targetLanguage) {
        String prompt = buildTranslationPrompt(text, targetLanguage);
        return GeminiRequest.of(prompt);
    }

    private String buildGrammarCorrectionPrompt(String text) {
//        return String.format("""
//            You are a grammar expert. Fix grammatical errors in the following text.
//
//            Rules:
//            1. Fix only grammatical errors (spelling, punctuation, agreement)
//            2. Preserve the original meaning and style of the text
//            3. Do not add any extra words or phrases
//            4. Return only the corrected text without explanations
//            5. IMPORTANT: The corrected text MUST be in the same language as the input text. NEVER translate the text to another language
//
//            Text to correct:
//            %s
//            """, text);

        return String.format("""
                You are a professional grammar corrector.
                
                Instructions:
                1. Carefully correct all grammar, punctuation, spelling, agreement, and word usage mistakes.
                2. Preserve the original meaning and tone of the text.
                3. You may make light rephrasings if it improves correctness or naturalness.
                4. Return only the corrected version, without explanations or comments.
                5. The corrected text must be in the same language as the input.
                
                Text to correct:
                %s
                """, text);
    }

    private String buildTranslationPrompt(String text, String targetLanguage) {
        return String.format("""
                You are a language expert. Follow these instructions carefully:
                1. First, accurately detect the language of this text: "%s"
                2. If the detected language matches the target language (%s):
                   - Return: "DETECTED_LANGUAGE|ORIGINAL_TEXT" where DETECTED_LANGUAGE is the actual language name
                3. Otherwise, translate the text to %s
                
                Return ONLY in format: DETECTED_LANGUAGE|TRANSLATION
                Do not include any additional text or explanations.
                """, text, targetLanguage, targetLanguage);
    }
} 