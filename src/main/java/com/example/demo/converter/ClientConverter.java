package com.example.demo.converter;

import com.example.demo.domain.Client;
import com.example.demo.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {
    public ClientDto toDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .build();
    }

    public Client toEntity(ClientDto dto) {
        return Client.builder()
                .id(dto.getId())
                .build();
    }
}
