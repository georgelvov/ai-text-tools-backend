package com.glvov.aitexttoolsbackend.core.model.response;

import lombok.Data;

import java.util.List;

@Data
public class GeminiResponse {
    private List<Candidate> candidates;
    private UsageMetadata usageMetadata;
    private String modelVersion;
    private String responseId;

    public String getFirstResponseText() {
        if (candidates != null && !candidates.isEmpty()) {
            Candidate firstCandidate = candidates.getFirst();
            if (firstCandidate.getContent() != null
                && firstCandidate.getContent().getParts() != null
                && !firstCandidate.getContent().getParts().isEmpty()) {
                return firstCandidate.getContent().getParts().getFirst().getText();
            }
        }

        return null;
    }
}


