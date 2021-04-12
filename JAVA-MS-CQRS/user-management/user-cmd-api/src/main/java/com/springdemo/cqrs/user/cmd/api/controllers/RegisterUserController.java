package com.springdemo.cqrs.user.cmd.api.controllers;

import com.springdemo.cqrs.user.cmd.api.commands.RegisterUserCommand;
import com.springdemo.cqrs.user.cmd.api.dto.RegisterUserResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping( path="/api/v1/registeruser")
public class RegisterUserController {

    private final CommandGateway commandGateway;

    @Autowired
    public RegisterUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> rigisterUser(@Valid  @RequestBody RegisterUserCommand command)
    {
        var id=UUID.randomUUID().toString();
        command.setId(id);
        try{


            commandGateway.send(command);
            return new ResponseEntity<>(new RegisterUserResponse(id, "user successfully registered"),HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return new ResponseEntity<>(new RegisterUserResponse(id,"error in registring user"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }//rigisterUser
}
