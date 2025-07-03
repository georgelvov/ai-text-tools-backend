package com.glvov.aitexttoolsbackend.core.prompt;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class PromptLoader {

    private static final String PROMPT_PATH_TEMPLATE = "classpath:prompts/%s.txt";

    private final Map<String, String> promptCache = new ConcurrentHashMap<>();
    private final ResourceLoader resourceLoader;


    public String loadPrompt(String promptName, Object... args) {
        try {
            return promptCache
                    .computeIfAbsent(promptName, this::loadPromptFromResource)
                    .formatted(args);
        } catch (Exception e) {
            throw new PromptLoadException("Failed to format prompt: " + promptName, e);
        }
    }

    @SneakyThrows
    private String loadPromptFromResource(String promptName) {
        String resourcePath = PROMPT_PATH_TEMPLATE.formatted(promptName);

        Resource resource = resourceLoader.getResource(resourcePath);

        if (!resource.exists()) {
            throw new FileNotFoundException(resourcePath);
        }

        String content = resource.getContentAsString(StandardCharsets.UTF_8);

        log.debug("Successfully loaded prompt '{}' from {}", promptName, resourcePath);

        return content.trim();
    }

    public static class PromptLoadException extends RuntimeException {
        public PromptLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
