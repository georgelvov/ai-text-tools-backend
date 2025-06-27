package com.glvov.aitexttoolsbackend.core.config;

import com.glvov.aitexttoolsbackend.core.AIModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@Log4j2
public class GeminiConfig {

    @Value("${gemini.api.key}")
    private String apiKey;


    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter(logRequest())
                .build();
    }

    public String getApiUrl(AIModel model) {
        return String.format(
                "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent?key=%s",
                model.getValue(),
                apiKey);
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            log.info("Executing request: {} {}", request.method(), request.url());
            log.debug("Request headers: {}", request.headers());
            return Mono.just(request);
        });
    }
}
