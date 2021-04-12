package com.springdemo.cqrs.user.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class RemoveUserCommand {
    @TargetAggregateIdentifier //for axon server to know when target Aggregator to identify the oject
    private String id;
}
