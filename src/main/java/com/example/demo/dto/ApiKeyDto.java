package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ApiKeyDto {
    UUID id;
    String apiKey;
    List<UUID> clientsIds;
}
