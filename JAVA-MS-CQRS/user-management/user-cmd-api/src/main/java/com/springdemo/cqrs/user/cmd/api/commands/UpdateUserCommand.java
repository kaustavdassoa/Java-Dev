package com.springdemo.cqrs.user.cmd.api.commands;

import com.springdemo.cqrs.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier //for axon server to know when target Aggregator to identify the oject
    private String id;
    private User user;

}
