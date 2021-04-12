package com.springdemo.cqrs.user.query.api.handlers;

import com.springdemo.cqrs.user.query.api.dto.UserLookupResponse;
import com.springdemo.cqrs.user.query.api.query.FindAllUsersQuery;
import com.springdemo.cqrs.user.query.api.query.FindUserByIdQuery;
import com.springdemo.cqrs.user.query.api.query.SearchUsersQuery;

public interface UserQueryHandler {

    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
