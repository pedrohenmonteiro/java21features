package com.pedromonteiro.java21.domain;

public sealed interface Password permits PlainTextPassword, SHA1Password{
 
    String value();

    boolean validate(String password);

}
