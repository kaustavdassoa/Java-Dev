package com.springdemo.cqrs.user.core.events;

import lombok.Data;

@Data
public class UserRemoveEvent {
    private String id;
}
