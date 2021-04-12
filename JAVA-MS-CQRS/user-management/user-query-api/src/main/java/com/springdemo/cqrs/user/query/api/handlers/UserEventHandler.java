package com.springdemo.cqrs.user.query.api.handlers;

import com.springdemo.cqrs.user.core.events.UserRegisterEvent;
import com.springdemo.cqrs.user.core.events.UserRemoveEvent;
import com.springdemo.cqrs.user.core.events.UserUpdateEvent;

public interface UserEventHandler {

    public void on(UserRegisterEvent event);
    public void on(UserUpdateEvent event);
    public void on(UserRemoveEvent event);
}
