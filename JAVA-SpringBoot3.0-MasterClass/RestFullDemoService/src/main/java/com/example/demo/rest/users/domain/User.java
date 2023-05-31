package com.example.demo.rest.users.dao;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {


    private Integer id;
    @Min(value = 7 , message = "Name should be minimum 7 char long")
    private String name;
    @Past(message = "Birth Date should be in the past")
    private LocalDate dateOfBirth;




}
