package com.example.demo.repo;

import com.example.demo.domain.ApiKey;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ApiKeyRepo extends ListCrudRepository<ApiKey, UUID> {

}
