package com.glvov.aitexttoolsbackend.core.model.request;

import com.glvov.aitexttoolsbackend.core.model.Content;
import com.glvov.aitexttoolsbackend.core.model.Part;
import lombok.Data;

import java.util.List;

@Data
public class GeminiRequest {

    private List<Content> contents;

    public static GeminiRequest of(String text) {
        GeminiRequest request = new GeminiRequest();
        Content content = new Content();
        Part part = new Part();
        part.setText(text);
        content.setParts(List.of(part));
        request.setContents(List.of(content));
        return request;
    }
}

