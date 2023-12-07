package com.pedromonteiro.java21.application.usecases;

import com.pedromonteiro.java21.application.repositories.UserRepository;
import com.pedromonteiro.java21.domain.PasswordType;
import com.pedromonteiro.java21.domain.User;

public class CreateUser {
    
    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Output execute(final Input input) {


       var aUser = this.userRepository.save(User.create(input.email, input.passwordType, input.password));

       return new Output(aUser.id());
    }

    public record Input(String email, PasswordType passwordType, String password) {}

    public record Output(String userId) {}
    
}
