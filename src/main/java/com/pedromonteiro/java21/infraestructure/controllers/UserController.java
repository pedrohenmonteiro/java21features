package com.pedromonteiro.java21.infraestructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedromonteiro.java21.application.usecases.CreateUser;
import com.pedromonteiro.java21.domain.User;
import com.pedromonteiro.java21.infraestructure.dtos.CreateUserInput;

import java.util.Map;
import java.util.Objects;
import java.net.URI;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final CreateUser createUser;

        

    public UserController(final CreateUser createUser) {
        this.createUser = Objects.requireNonNull(createUser);
    }



    @PostMapping
    public ResponseEntity<?> create(
        @RequestBody CreateUserInput input
    ) {
        try {
            final var output = 
            this.createUser.execute(new CreateUser.Input(input.email(), input.passwordType(), input.password()));

        return ResponseEntity.created(URI.create("/users/%s".formatted(output.userId()))).body(output);   
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().body(Map.of("error", t.getMessage()));
        }

        
    }

}
