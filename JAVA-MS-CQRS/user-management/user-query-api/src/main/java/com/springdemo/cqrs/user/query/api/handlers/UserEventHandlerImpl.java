package com.springdemo.cqrs.user.query.api.handlers;

import com.springdemo.cqrs.user.core.events.UserRegisterEvent;
import com.springdemo.cqrs.user.core.events.UserRemoveEvent;
import com.springdemo.cqrs.user.core.events.UserUpdateEvent;
import com.springdemo.cqrs.user.query.api.repositories.UserRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user-group") //Axon specific annotation
public class UserEventHandlerImpl implements  UserEventHandler{


    private final UserRepository userRepository;

    @Autowired
    public UserEventHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler // it defines the buisiness logic when a specific event is consume by the event bus.
    @Override
    public void on(UserRegisterEvent event) {
        userRepository.save(event.getUser()); // saving the new-user info on mongo-db
    }

    @EventHandler // it defines the buisiness logic when a specific event is consume by the event bus.
    @Override
    public void on(UserUpdateEvent event) {
        userRepository.save(event.getUser()); // updating the user info on mongo-db
    }

    @EventHandler // it defines the buisiness logic when a specific event is consume by the event bus.
    @Override
    public void on(UserRemoveEvent event) {
        userRepository.deleteById(event.getId()); // deleting user info
    }
}
