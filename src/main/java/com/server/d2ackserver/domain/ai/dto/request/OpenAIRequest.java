package com.server.d2ackserver.domain.ai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenAIRequest {
    private String model = "gpt-4o";
    private List<Message> messages;

    @JsonProperty("max_tokens")
    private Integer maxTokens = 1000;

    private Double temperature = 0.3;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}
