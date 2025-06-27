package com.glvov.aitexttoolsbackend.core.model.response;

import lombok.Data;

import java.util.List;

@Data
public class UsageMetadata {
    private int promptTokenCount;
    private int candidatesTokenCount;
    private int totalTokenCount;
    private List<PromptTokenDetail> promptTokensDetails;
    private int thoughtsTokenCount;
}
