package com.springdemo.cqrs.user.query.api.dto;

import com.springdemo.cqrs.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserLookupResponse {

    private List<User> users;

    public UserLookupResponse(User user) {
        this.users=new ArrayList<>();
        this.users.add(user);
    }


}
