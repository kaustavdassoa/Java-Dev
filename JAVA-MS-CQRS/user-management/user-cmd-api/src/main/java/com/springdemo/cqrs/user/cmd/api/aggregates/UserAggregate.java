package com.springdemo.cqrs.user.cmd.api.aggregates;

import com.springdemo.cqrs.user.cmd.api.commands.RegisterUserCommand;
import com.springdemo.cqrs.user.cmd.api.commands.RemoveUserCommand;
import com.springdemo.cqrs.user.cmd.api.commands.UpdateUserCommand;
import com.springdemo.cqrs.user.cmd.api.security.PasswordEncoder;
import com.springdemo.cqrs.user.cmd.api.security.PasswordEncoderImpl;
import com.springdemo.cqrs.user.core.events.UserRegisterEvent;
import com.springdemo.cqrs.user.core.events.UserRemoveEvent;
import com.springdemo.cqrs.user.core.events.UserUpdateEvent;
import com.springdemo.cqrs.user.core.models.User;
import lombok.extern.java.Log;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;


@Log
@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private String id;
    private User user;
    private final PasswordEncoder passwordEncoder;



    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        var newUser = command.getUser();
        newUser.setId(command.getId());
        var password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        var hashedPassword = passwordEncoder.hashPassword(password);
        newUser.getAccount().setPassword(hashedPassword);

        var event = UserRegisterEvent.builder()
                .id(command.getId())
                .user(newUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        var password = updatedUser.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        updatedUser.getAccount().setPassword(hashedPassword);

        var event = UserUpdateEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var event = new UserRemoveEvent();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisterEvent event) {
        //if this value is not mapped it will return the followig error
        //resulted in org.axonframework.commandhandling.CommandExecutionException(Aggregate identifier must be non-null after applying an event. Make sure the aggregate identifier is initialized at the latest when handling the creation event
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemoveEvent event) {
        AggregateLifecycle.markDeleted();
    }


    /**
     * //Default Constructor
     *     UserAggregate(){
     *         this.passwordEncoder=new PasswordEncoderImpl();
     *     }
     *
     *     @CommandHandler
     *     public UserAggregate(RegisterUserCommand command)
     *     {
     *         log.info("inside CommandHandler-UserAggregates");
     *         var newUser=command.getUser();
     *         newUser.setId(command.getId());
     *         var password= newUser.getAccount().getPassword();//PlainText Password
     *         this.passwordEncoder=new PasswordEncoderImpl();
     *         var hashPassword = passwordEncoder.hashPassword(password);
     *         newUser.getAccount().setPassword(hashPassword);
     *
     *         //Create a new RegisterEvent
     *         var event=UserRegisterEvent.builder()
     *                 .id(command.getId())
     *                 .user(newUser)
     *                 .build();
     *
     *         log.info("event :"+event.toString());
     *         //Saving the new registerUserEvent to the event bus
     *         AggregateLifecycle.apply(event);
     *         log.info("successfully save event to AggregateLifecycle");
     *     }
     *
     *     @CommandHandler
     *     public void handle(UpdateUserCommand commnad)
     *     {
     *         var updateUser= commnad.getUser();
     *         updateUser.setId(commnad.getId());
     *         var password= commnad.getUser().getAccount().getPassword();
     *         var hashPassword = passwordEncoder.hashPassword(password);
     *         updateUser.getAccount().setPassword(hashPassword);
     *
     *         //Create a new UpdateEvent
     *         var event=UserUpdateEvent.builder()
     *                 .id(UUID.randomUUID().toString())
     *                 .user(updateUser)
     *                 .build();
     *         //Saving the new updateEvent to the event bus
     *         AggregateLifecycle.apply(event);
     *     }
     *
     *     @CommandHandler
     *     public void handle(RemoveUserCommand commnad)
     *     {
     *         //Create a new Remove
     *         var event= new RemoveUserCommand();
     *         event.setId(commnad.getId());
     *         //Saving the new removeEvent to the event bus
     *         AggregateLifecycle.apply(event);
     *     }
     *
     *     @EventSourcingHandler
     *     public void on(UserRegisterEvent event)
     *     {
     *         log.info("inside EventSourcingHandler-UserRegisterEvent"+event.toString());
     *         this.user=event.getUser();
     *     }
     *
     *     @EventSourcingHandler
     *     public void on(UserUpdateEvent event)
     *     {
     *         this.id=event.getId();
     *         this.user=event.getUser();
     *     }
     *
     *
     *     @EventSourcingHandler
     *     public void on(UserRemoveEvent event)
     *     {
     *         AggregateLifecycle.markDeleted();
     *     }
     */
}
