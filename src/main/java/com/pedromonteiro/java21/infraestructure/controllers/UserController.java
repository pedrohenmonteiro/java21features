package com.pedromonteiro.java21.infraestructure.controllers;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedromonteiro.java21.application.usecases.CreateUser;
import com.pedromonteiro.java21.application.usecases.GetUserOfId;
import com.pedromonteiro.java21.infraestructure.dtos.CreateUserInput;


@RestController
@RequestMapping(path = "users")
public class UserController {

    private final CreateUser createUser;
    private final GetUserOfId getUserOfId;

        

    public UserController(final CreateUser createUser, final GetUserOfId getUserOfId) {
        this.createUser = Objects.requireNonNull(createUser);
        this.getUserOfId = Objects.requireNonNull(getUserOfId);
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


    @GetMapping("/{id}")
    public ResponseEntity<?> userOfId(@PathVariable String id) {
        try {
            final var output = this.getUserOfId.execute(new GetUserOfId.Input(id));
            return ResponseEntity.ok(output);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
