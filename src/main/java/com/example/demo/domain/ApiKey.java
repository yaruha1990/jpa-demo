package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "public", name = "api_key")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey {
    @Id
    @UuidGenerator
    @Column(name = "id")
    UUID id;
    @Column(name = "api_key")
    String apiKey;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "apiKey")
    List<Client> clients;
}
