package com.example.demo.controller;

import com.example.demo.domain.ApiKey;
import com.example.demo.domain.Client;
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

    @GetMapping
    public List<ApiKey> findAllApiKeys() {
        return apiKeyRepo.findAll();
    }

    @PostMapping
    public ApiKey save(@RequestBody ApiKey apiKey) {
        final List<UUID> clientsIds = apiKey.getClients().stream().map(Client::getId).toList();
        final List<Client> existingClients = clientRepo.findAllById(clientsIds);

        existingClients.forEach(c -> c.setApiKey(apiKey));

        clientRepo.saveAll(existingClients);
        return apiKeyRepo.save(apiKey);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Optional<ApiKey> byId = apiKeyRepo.findById(id);
        if (byId.isPresent()) {
            ApiKey apiKey = byId.get();
            apiKey.getClients().forEach(c -> c.setApiKey(null));
            clientRepo.saveAll(apiKey.getClients());
        }
    }
}