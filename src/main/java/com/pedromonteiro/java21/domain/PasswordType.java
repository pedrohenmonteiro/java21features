package com.pedromonteiro.java21.domain;

import java.util.function.Function;

public enum PasswordType {

    PLAIN(PlainTextPassword::create, PlainTextPassword::restore),
    SHA1(SHA1Password::create, SHA1Password::restore),
    PBKDF2(PBKDF2Password::create, PBKDF2Password::restore);

    private final Function <String, Password> createFn;

    private final Function <String, Password> restoreFn;

    private PasswordType(Function<String, Password> createFn, Function<String, Password> restoreFn) {
        this.createFn = createFn;
        this.restoreFn = restoreFn;
    }


    public Password create(final String plainPassword) {
        return createFn.apply(plainPassword);
    }

    
    public Password restore(final String password) {
        return restoreFn.apply(password);
    }
    















    // public static String create(String passwordType, String password) {
    //     Password passwordStrategy = new PlainTextPassword("123455");

    //     var plainPass = switch (passwordStrategy) {
    //         //record patterns
    //         case PlainTextPassword(String pass) -> pass;
    //         case SHA1Password(String pass) -> pass;
    //         case PBKDF2Password p -> p.value();
    //     };


    //     //   var plainPass = switch (passwordStrategy) {
    //     //     //record patterns
    //     //     case Password p when p instanceof PlainTextPassword(String pass) -> pass;
    //     //     case Password p when p instanceof SHA1Password(String shapass) -> pass;
    //     //     case PBKDF2Password p -> p.value();
    //     // };


    //     return plainPass;
    // }
}
