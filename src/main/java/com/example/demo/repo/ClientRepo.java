package com.example.demo.repo;

import com.example.demo.domain.Client;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ClientRepo extends ListCrudRepository<Client, UUID> {
}
