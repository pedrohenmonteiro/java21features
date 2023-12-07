package com.pedromonteiro.java21.infraestructure.dtos;

import com.pedromonteiro.java21.domain.PasswordType;

public record CreateUserInput(String email, PasswordType passwordType, String password) {
    
}
