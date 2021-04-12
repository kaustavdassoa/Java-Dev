package com.springdemo.cqrs.user.cmd.api.controllers;

import com.springdemo.cqrs.user.cmd.api.commands.RemoveUserCommand;
import com.springdemo.cqrs.user.cmd.api.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deleteuser")
public class RemovesUserController {


    private final CommandGateway commandGateway;

    @Autowired
    public RemovesUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id) {

        try {

            commandGateway.send(new RemoveUserCommand(id));
            return new ResponseEntity(new BaseResponse("user sucessfully deleted request id :" + id), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity(new BaseResponse("error in processing update user request"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
