package com.example.demo.rest.users.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(name = "BIRTH_DATE")
    private LocalDate birthdate;


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> post;
}
