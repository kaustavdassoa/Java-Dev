package com.springdemo.cqrs.user.query.api.handlers;

import com.springdemo.cqrs.user.query.api.dto.UserLookupResponse;
import com.springdemo.cqrs.user.query.api.query.FindAllUsersQuery;
import com.springdemo.cqrs.user.query.api.query.FindUserByIdQuery;
import com.springdemo.cqrs.user.query.api.query.SearchUsersQuery;
import com.springdemo.cqrs.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class UserQueryHandlerImpl implements UserQueryHandler{

    private final UserRepository userRepository;

    @Autowired
    public UserQueryHandlerImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
       var user=this.userRepository.findById(query.getId());
      return new UserLookupResponse(user.isPresent() ? user.get() : null);
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        //TODO: String filter to be copied from Video 12
        return null;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        return new UserLookupResponse(new ArrayList<>(this.userRepository.findAll()));
    }
}
