package com.springdemo.cqrs.user.cmd.api.controllers;

import com.springdemo.cqrs.user.cmd.api.commands.UpdateUserCommand;
import com.springdemo.cqrs.user.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping( path="/api/v1/updateuser")
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @Autowired
    public UpdateUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id, @Valid @RequestBody UpdateUserCommand command)
    {
        try{
            command.setId(id);
            commandGateway.send(command);
            return new ResponseEntity(new BaseResponse("user sucessfully udpated"),HttpStatus.OK);

        }catch(Exception e)
        {
            System.out.println(e.toString());
            return new ResponseEntity(new BaseResponse("error in processing update user request"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
