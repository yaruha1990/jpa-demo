package com.example.demo.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "app.security.endpoints")
public class SecurityProperties {
    List<String> whiteList;
}
