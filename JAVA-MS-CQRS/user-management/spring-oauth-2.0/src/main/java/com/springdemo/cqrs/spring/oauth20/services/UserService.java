package com.springdemo.cqrs.spring.oauth20.services;

import com.springdemo.cqrs.spring.oauth20.repositories.UserRepositories;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Log
@Service (value="userService")
public class UserService implements UserDetailsService {

    private UserRepositories userRepositories;

    @Autowired
    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user=this.userRepositories.findByUserName(userName);
        log.info("Fetched User detail "+user.toString());
        if(user.isEmpty())
        {
            throw new UsernameNotFoundException("user :"+userName+" not found");
        }
        var account=user.get().getAccount();

        return org.springframework.security.core.userdetails.User
                .withUsername(userName)
                .password(account.getPassword())
                .authorities(account.getRoles())
                .accountExpired(false) //TODO: Set accountExpired field on DB
                .accountLocked(false) //TODO: Set accountLocked field on DB
                .credentialsExpired(false) //TODO: Set credentialsExpired field on DB
                .disabled(false) //TODO: Set accountDisable field on DB
                .build();
    }
}
