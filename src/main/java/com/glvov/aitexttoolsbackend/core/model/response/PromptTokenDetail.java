package com.glvov.aitexttoolsbackend.core.model.response;

import lombok.Data;

@Data
public class PromptTokenDetail {
    private String modality;
    private int tokenCount;
}
