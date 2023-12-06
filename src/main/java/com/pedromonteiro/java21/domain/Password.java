package com.pedromonteiro.java21.domain;

public interface Password {
 
    String value();

    boolean validate(String password);

}
