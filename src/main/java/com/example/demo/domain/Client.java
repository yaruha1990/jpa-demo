package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(schema = "public", name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = "apiKey")
@Builder
public class Client {
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;
    @Column(name = "api_key_id")
    UUID apiKeyId;
}
