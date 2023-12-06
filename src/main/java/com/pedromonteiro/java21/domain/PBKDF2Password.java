package com.pedromonteiro.java21.domain;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.google.common.io.BaseEncoding;


public record PBKDF2Password(String value, String salt) implements Password{

    @Override
    public String value() {
        return "%s$$%s".formatted(value, salt);
    }


    public static PBKDF2Password create(final String plainPassword) {
        var salt = generateSalt();
        return new PBKDF2Password(hash(plainPassword, salt), salt); 
    }

    
    public static PBKDF2Password restore(String password) {
        var allparts = password.split("\\$\\$");
        var pass = allparts[0];
        var salt = allparts[1];
        return new PBKDF2Password(pass, salt);
    }

    @Override
    public boolean validate(String password) {
        return value().equals(hash(password, salt));
    }

    private static String hash(final String plainText, final String salt) {
       try {
        var spec = new PBEKeySpec(plainText.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 100, 64);
        var pass = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
            .generateSecret(spec)
            .getEncoded();
        return BaseEncoding.base64().encode(pass);
       } catch (Throwable t) {
        t.printStackTrace();
        throw new RuntimeException(t);
       }
    }


    private static String generateSalt() {
        var bytes = new byte[16];
        var random = new SecureRandom();
        random.nextBytes(bytes);

        return BaseEncoding.base16().encode(bytes);
    }

    
}
