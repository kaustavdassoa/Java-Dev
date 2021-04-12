package com.springdemo.cqrs.user.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data // Lombok to generate getter and setter and toString methods
@AllArgsConstructor // Lombok to generate all arugments constructor
@NoArgsConstructor // Lombok to generate no arugment constructor
@Builder //Lombok to generate complex builder
@Document (collection = "users") // For mongo db to identify as domain object to persit
public class User {

    @Id
    private String id;
    @NotEmpty(message = "firstNmae cannot be empty")
    private String firstName;
    @NotEmpty(message = "lastName cannot be empty")
    private String lastName;
    @Email(message = "email is not a valid")
    private String emailAddress;
    @NotNull (message = "account details requried")
    private Account account;
}
