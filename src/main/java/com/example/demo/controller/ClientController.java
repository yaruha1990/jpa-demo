package com.example.demo.controller;

import com.example.demo.domain.Client;
import com.example.demo.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepo clientRepo;

    @GetMapping
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @PostMapping
    public Client save(@RequestBody Client client) {
        return clientRepo.save(client);
    }
}
