package com.pedromonteiro.java21.infraestructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pedromonteiro.java21.application.repositories.UserRepository;
import com.pedromonteiro.java21.application.usecases.CreateUser;

@Configuration
public class UseCasesConfig {
    
    private final UserRepository userRepository;

    public UseCasesConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public CreateUser createUser() {
        return new CreateUser(userRepository);
    }
}
