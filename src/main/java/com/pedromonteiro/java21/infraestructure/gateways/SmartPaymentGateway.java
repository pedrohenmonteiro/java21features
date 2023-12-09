package com.pedromonteiro.java21.infraestructure.gateways;

import com.pedromonteiro.java21.application.gateways.PaymentGateways;
import com.pedromonteiro.java21.domain.PaymentMethod;

public class SmartPaymentGateway implements PaymentGateways {

    @Override
    public Output execute(Input input) {
        var gateway = switch (input.paymentMethod()) {
            case PaymentMethod.CreditCard cc when "visa".equalsIgnoreCase(cc.brand()) -> new VisaGateway();
            case PaymentMethod.CreditCard cc when "mastercard".equalsIgnoreCase(cc.brand()) -> new MastercardGateway();
            case PaymentMethod.CreditCard cc -> new EloGateway();
            case PaymentMethod.PIX p -> new PIXGateway();
        };

        return gateway.execute(input);
    }
}
