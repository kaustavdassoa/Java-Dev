package com.springdemo.cqrs.user.cmd.api.commands;

import com.springdemo.cqrs.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RegisterUserCommand {

    @TargetAggregateIdentifier //for axon server to know when target Aggregator to identify the oject
    private String id;
    @NotNull(message = "no user details were supplied")
    @Valid
    private User user;
}
