package com.pedromonteiro.java21.domain;

import java.util.UUID;


public final class IdGenerator {

    public static String nextId(){
        return UUID.randomUUID().toString();
    }
    
}
