package com.example.simple.saga.steps.payments.service;

public enum PaymentType {
    PAYMENT_GATEWAY("Payment Gateway"),
    CREDIT_CARD("Credit Card");

    private final String displayName;

    PaymentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
