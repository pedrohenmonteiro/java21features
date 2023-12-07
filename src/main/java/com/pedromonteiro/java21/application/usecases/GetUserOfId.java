package com.pedromonteiro.java21.application.usecases;

import java.util.Objects;

import com.pedromonteiro.java21.application.repositories.UserRepository;
import com.pedromonteiro.java21.domain.User;

public class GetUserOfId {



    private final UserRepository userRepository;

    public GetUserOfId(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public Output execute(final Input input) {

        return this.userRepository.userOfId(input.id) 
            .map(Output::new)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public record Input(String id) {}

    public record Output(String userId, String email) {

        public Output(User user) {
            this(user.id(), user.email());
        }
    }
}
