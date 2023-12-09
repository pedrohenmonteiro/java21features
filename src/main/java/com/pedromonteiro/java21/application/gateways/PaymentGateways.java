package com.pedromonteiro.java21.application.gateways;

import com.pedromonteiro.java21.domain.PaymentMethod;

public interface PaymentGateways {

    Output execute(Input input);
    

     record Input(PaymentMethod paymentMethod) {

    }

     record Output(String transactionId) {}
}
