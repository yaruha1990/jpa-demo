package com.example.demo.controller;

import com.example.demo.domain.ApiKey;
import com.example.demo.domain.Client;
import com.example.demo.repo.ApiKeyRepo;
import com.example.demo.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api-key")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyRepo apiKeyRepo;
    private final ClientRepo clientRepo;

    @GetMapping
    public List<ApiKey> findAllApiKeys() {
        return apiKeyRepo.findAll();
    }

    @PostMapping
    public ApiKey create(@RequestBody ApiKey apiKey) {
        final List<UUID> clientsIds = apiKey.getClients().stream().map(Client::getId).toList();
        final List<Client> existingClients = clientRepo.findAllById(clientsIds);

        if (existingClients.size() != apiKey.getClients().size()) {
            throw new RuntimeException("Client does not exist");
        }
        existingClients.forEach(c -> c.setApiKey(apiKey));
        apiKey.setClients(existingClients);
        return apiKeyRepo.save(apiKey);
    }

    @PutMapping
    public void update(@RequestBody ApiKey apiKey) {
        final Optional<ApiKey> existingApiKey = apiKeyRepo.findById(apiKey.getId());
        if (existingApiKey.isEmpty()) {
            throw new RuntimeException("API-KEY does not exist");
        }
        final List<Client> clientsForUpdate = apiKey.getClients();
        if (clientsForUpdate == null) {
            return;
        }

        final List<Client> oldClients = clientRepo.findAllById(existingApiKey.get()
                .getClients()
                .stream()
                .map(Client::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        oldClients.forEach(c -> c.setApiKey(null));
        clientRepo.saveAll(oldClients);

        clientsForUpdate.forEach(c -> c.setApiKey(apiKey));
        clientRepo.saveAll(clientsForUpdate);
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
        Optional<ApiKey> byId = apiKeyRepo.findById(id);
        if (byId.isPresent()) {
            ApiKey apiKey = byId.get();
            apiKey.getClients().forEach(c -> c.setApiKey(null));
            clientRepo.saveAll(apiKey.getClients());
            apiKeyRepo.deleteById(apiKey.getId());
        }
    }
}
