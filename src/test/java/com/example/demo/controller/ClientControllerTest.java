package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebSecurity
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() {
        BadCredentialsException exception = assertThrows(BadCredentialsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                mockMvc.perform(MockMvcRequestBuilders.get("/client").header("x-api-key", "valid-key-"));
            }
        });

        assertThat(exception.getMessage()).isEqualTo("Invalid API KEY");
    }

}