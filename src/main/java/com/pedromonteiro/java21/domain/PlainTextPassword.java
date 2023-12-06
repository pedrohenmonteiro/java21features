package com.pedromonteiro.java21.domain;

public record PlainTextPassword(String value) implements Password{

    public static PlainTextPassword create(String plainPassword) {
        return new PlainTextPassword(plainPassword);
    }

    public static PlainTextPassword restore(String password) {
        return new PlainTextPassword((password));
    }


    @Override
    public boolean validate(String password) {
        return value().equals(password);
    }
    
}
