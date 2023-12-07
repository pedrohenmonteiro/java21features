package com.pedromonteiro.java21.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordTypeTest {
    
    @Test
    public void deveriaCriarPlainTextPassword() {
        final var expectedPassword = "123456";
        final var actualPassword = PasswordType.PLAIN.create(expectedPassword);

        Assertions.assertEquals(expectedPassword, actualPassword.value());
    }
}
