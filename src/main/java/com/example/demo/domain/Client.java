package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
public class Client {
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;

    @ManyToOne
    @JoinColumn(name = "api_key_id")
    ApiKey apiKey;
}
