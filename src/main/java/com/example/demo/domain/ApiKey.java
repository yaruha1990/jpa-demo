package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(schema = "public", name = "api_key")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiKey {
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;
    @Column(name = "api_key")
    String apiKey;
}
