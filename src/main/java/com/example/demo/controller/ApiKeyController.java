package com.example.demo.controller;

import com.example.demo.converter.ApiKeyConverter;
import com.example.demo.converter.ClientConverter;
import com.example.demo.domain.ApiKey;
import com.example.demo.domain.Client;
import com.example.demo.dto.ApiKeyDto;
import com.example.demo.repo.ApiKeyRepo;
import com.example.demo.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api-key")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyRepo apiKeyRepo;
    private final ClientRepo clientRepo;
    private final ApiKeyConverter apiKeyConverter;
    private final ClientConverter clientConverter;

    @GetMapping
    public List<ApiKeyDto> findAllApiKeys() {
        final List<Client> clients = clientRepo.findAll();
        final List<ApiKey> apiKeys = apiKeyRepo.findAll();

        return apiKeys.stream().map(apiKey -> {

            final List<UUID> clientsIds = clients.stream()
                    .filter(client -> client.getApiKeyId().equals(apiKey.getId()))
                    .map(Client::getId)
                    .toList();

            return ApiKeyDto.builder()
                    .id(apiKey.getId())
                    .apiKey(apiKey.getApiKey())
                    .clientsIds(clientsIds)
                    .build();
        }).toList();
    }

    @PostMapping
    public ApiKey create(@RequestBody ApiKeyDto apiKeyDto) {
        final ApiKey apiKey = apiKeyConverter.toEntity(apiKeyDto);
        apiKey.setApiKey(UUID.randomUUID().toString());
        final ApiKey saved = apiKeyRepo.save(apiKey);

        final List<Client> clients = apiKeyDto.getClientsIds()
                .stream()
                .map(uuid -> Client.builder().id(uuid).build())
                .toList();

        clients.forEach(c -> c.setApiKeyId(saved.getId()));
        clientRepo.saveAll(clients);

        return saved;
    }

    @PutMapping
    public void update(@RequestBody ApiKey apiKey) {
//        final Optional<ApiKey> existingApiKey = apiKeyRepo.findById(apiKey.getId());
//        if (existingApiKey.isEmpty()) {
//            throw new RuntimeException("API-KEY does not exist");
//        }
//        final List<Client> clientsForUpdate = apiKey.getClients();
//        if (clientsForUpdate == null) {
//            return;
//        }
//
//        final List<Client> oldClients = clientRepo.findAllById(existingApiKey.get()
//                .getClients()
//                .stream()
//                .map(Client::getId)
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList()));
//
//        oldClients.forEach(c -> c.setApiKey(null));
//        clientRepo.saveAll(oldClients);
//
//        clientsForUpdate.forEach(c -> c.setApiKey(apiKey));
//        clientRepo.saveAll(clientsForUpdate);
    }

    @PatchMapping
    public ApiKey patch(@RequestBody ApiKey apiKey) {
        final Optional<ApiKey> existingApiKey = apiKeyRepo.findById(apiKey.getId());
        if (existingApiKey.isEmpty()) {
            throw new RuntimeException("API-KEY does not exist");
        }
        return apiKeyRepo.save(apiKey);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
//        Optional<ApiKey> byId = apiKeyRepo.findById(id);
//        if (byId.isPresent()) {
//            ApiKey apiKey = byId.get();
//            apiKey.getClients().forEach(c -> c.setApiKey(null));
//            clientRepo.saveAll(apiKey.getClients());
//            apiKeyRepo.deleteById(apiKey.getId());
//        }
    }
}
