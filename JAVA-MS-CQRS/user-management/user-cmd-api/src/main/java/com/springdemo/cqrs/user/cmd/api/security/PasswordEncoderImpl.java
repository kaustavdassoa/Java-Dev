package com.springdemo.cqrs.user.cmd.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder{
    @Override
    public String hashPassword(String password) {
        var encoder= new BCryptPasswordEncoder(12);
        var hasPassword = encoder.encode(password);
        return hasPassword;
    }
}
