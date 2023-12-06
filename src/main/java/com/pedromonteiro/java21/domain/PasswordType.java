package com.pedromonteiro.java21.domain;

public class PasswordType {
    
    public static String create(String passwordType, String password) {
        Password passwordStrategy = new PlainTextPassword("123455");

        var plainPass = switch (passwordStrategy) {
            case PlainTextPassword(String pass) -> pass;
            case SHA1Password(String pass) -> pass;
        };

        return plainPass;
    }
}
