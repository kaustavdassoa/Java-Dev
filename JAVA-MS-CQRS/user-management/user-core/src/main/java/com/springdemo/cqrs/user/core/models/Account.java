package com.springdemo.cqrs.user.core.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data // Lombok to generate getter and setter and toString methods
@AllArgsConstructor // Lombok to generate all arugments constructor
@NoArgsConstructor // Lombok to generate no arugment constructor
@Builder //Lombok to generate complex builder //TODO: Add explanation about builder DesignPattern
public class Account {

    @Size(min=2,message = "username should be at least 2 char long")
    private String userName;
    @Size(min=7,message = "pssword should be at least  7 char long")
    private String password;
    @NotNull(message = "at least one role is requeried")
    private List<Role> roles;
}
