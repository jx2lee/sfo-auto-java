package com.jx2lee.sfo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("dooray")
@Getter @Setter
public class DoorayConfig {
    private String token;
    @Value("{base-url}")
    private String baseUrl;
    @Value("{project-id}")
    private String projectId;

    public DoorayConfig() {
    }

    public DoorayConfig(String token, String baseUrl, String projectId) {
        this.token = token;
        this.baseUrl = baseUrl;
        this.projectId = projectId;
    }
}
