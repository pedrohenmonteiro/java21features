package com.pedromonteiro.java21.infraestructure.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedromonteiro.java21.domain.User;
import com.pedromonteiro.java21.infraestructure.dtos.CreateUserInput;

@RestController
@RequestMapping(path = "users")
public class UserController {

    @PostMapping
    public User create(
        @RequestBody CreateUserInput input
    ) {
        return User.create(input.email(), input.passwordType(), input.password());
    }

}
