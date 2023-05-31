package com.example.demo.rest.users.service;

import com.example.demo.rest.users.dao.User;
import com.example.demo.rest.users.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserManagementDaoService {

    private UserRepository userRepo;

    public UserManagementDaoService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    public Optional<List<User>> findAll()
    {
        return Optional.ofNullable(userRepo.findAll());
    }
}
