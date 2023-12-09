package com.pedromonteiro.java21.infraestructure.controllers;

import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedromonteiro.java21.application.usecases.CreateUser;
import com.pedromonteiro.java21.application.usecases.GetUserOfId;
import com.pedromonteiro.java21.application.usecases.ListUsers;
import com.pedromonteiro.java21.infraestructure.dtos.CreateUserInput;


@RestController
@RequestMapping(path = "users")
public class UserController {


    private static final ExecutorService EXECUTOR = 
            Executors.newVirtualThreadPerTaskExecutor();
    private final CreateUser createUser;
    private final GetUserOfId getUserOfId;
    private final ListUsers listUsers;

        

    public UserController(final CreateUser createUser, final GetUserOfId getUserOfId, final ListUsers listUsers) {
        this.createUser = Objects.requireNonNull(createUser);
        this.getUserOfId = Objects.requireNonNull(getUserOfId);
        this.listUsers = Objects.requireNonNull(listUsers);
    }



    @PostMapping
    public ResponseEntity<?> create(
        @RequestBody CreateUserInput input,
        @RequestParam(name = "latency", defaultValue = "10", required = false) int latency
    ) throws InterruptedException {
        Thread.sleep(latency);
        try {
            final var output = 
            this.createUser.execute(new CreateUser.Input(input.email(), input.passwordType(), input.password()));

        return ResponseEntity.created(URI.create("/users/%s".formatted(output.userId()))).body(output);   
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().body(Map.of("error", t.getMessage()));
        }
    }


    @GetMapping
    public List<ListUsers.Output> listUsers(
        @RequestParam(required = false, defaultValue = "", name ="ids") String ids
    ) {
        if (ids.isEmpty()) {
            return this.listUsers.execute();
        } else {
            var allIds = ids.split(",");
        
            var futures = Arrays.stream(allIds)
                .map(GetUserOfId.Input::new)
                .map(input -> EXECUTOR.submit(() -> this.getUserOfId.execute(input)))
                .toList();

        return futures.stream()
            .map(f -> {
                try {
                  return f.get();
                } catch (InterruptedException | ExecutionException ex) {
                   throw new RuntimeException(ex);
                }
            })
            .map(it -> new ListUsers.Output(it.userId(), it.email()))
            .toList();
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
