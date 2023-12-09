package com.pedromonteiro.java21.application.usecases;

import java.util.Objects;
import java.util.List;

import com.pedromonteiro.java21.application.repositories.UserRepository;
import com.pedromonteiro.java21.domain.User;

public class ListUsers {
    

    private final UserRepository userRepository;

    public ListUsers(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public List<Output> execute() {
        return this.userRepository.allUsers().stream()
                .map(Output::new)
                .toList();
    }
    
    public record Output(String userId, String email) {

        public Output(User user) {
            this(user.id(), user.email());
        }
    }
}
