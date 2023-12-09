package com.pedromonteiro.java21.domain;

public sealed interface PaymentMethod {

    record PIX(Double amount) implements PaymentMethod {

    }
    
    record CreditCard(String brand, Double amount, int installments) implements PaymentMethod {

    }
}
