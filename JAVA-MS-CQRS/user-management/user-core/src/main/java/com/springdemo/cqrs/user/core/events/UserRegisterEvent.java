package com.springdemo.cqrs.user.core.events;

import com.springdemo.cqrs.user.core.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterEvent {
    private String id;
    private User user;
}
