package com.server.d2ackserver.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Value("${openai.api.url:https://api.openai.com/v1/chat/completions}")
    private String openaiApiUrl;

    public String getApiKey() {
        return openaiApiKey;
    }

    public String getApiUrl() {
        return openaiApiUrl;
    }
}