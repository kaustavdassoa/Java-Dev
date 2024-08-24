package com.example.simple.saga.steps.account.service;

public enum AccountType {

    PRIME("Prime"),
    NON_PRIME("Non Prime");

    private final String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
