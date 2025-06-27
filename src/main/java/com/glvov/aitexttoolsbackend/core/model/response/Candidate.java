package com.glvov.aitexttoolsbackend.core.model.response;

import com.glvov.aitexttoolsbackend.core.model.Content;
import lombok.Data;

@Data
public class Candidate {
    private Content content;
    private String finishReason;
    private int index;
}
