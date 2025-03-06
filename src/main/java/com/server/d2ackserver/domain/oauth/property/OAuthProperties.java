package com.server.d2ackserver.domain.oauth.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oauth")
public class OAuthProperties {
    private Map<String, ProviderProperties> providers;

    @Getter
    @Setter
    public static class ProviderProperties {
        private String clientId;
        private String clientSecret;
        private String redirectUri;
    }
}

