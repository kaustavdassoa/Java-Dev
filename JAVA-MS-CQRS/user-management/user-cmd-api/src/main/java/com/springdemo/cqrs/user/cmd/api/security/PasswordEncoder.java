package com.springdemo.cqrs.user.cmd.api.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
