package com.pedromonteiro.java21.domain;
import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public record SHA1Password(String value) implements Password{

    public static SHA1Password create(String plainPassword) {
        return new SHA1Password(hash(plainPassword));
    }

    public static SHA1Password restore(String password) {
        return new SHA1Password(hash(password));
    }

    @Override
    public boolean validate(String password) {
        return value().equals(hash(password));
    }

    private static String hash(final String plainText) {
        return Hashing.sha1().hashString(plainText, StandardCharsets.UTF_8).toString();
    }
    
}
