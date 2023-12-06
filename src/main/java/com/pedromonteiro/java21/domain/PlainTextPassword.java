package com.pedromonteiro.java21.domain;

public record PlainTextPassword(String value) implements Password{

    @Override
    public boolean validate(String password) {
        return value().equals(password);
    }
    
}
