package com.example.demo.converter;

import com.example.demo.domain.ApiKey;
import com.example.demo.dto.ApiKeyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiKeyConverter {

    private final ClientConverter clientConverter;

    public ApiKeyDto toDto(ApiKey apiKey) {
        return ApiKeyDto.builder()
                .id(apiKey.getId())
                .apiKey(apiKey.getApiKey())
                .build();
    }

    public ApiKey toEntity(ApiKeyDto dto) {
        return ApiKey.builder()
                .id(dto.getId())
                .apiKey(dto.getApiKey())
                .build();
    }
}
