package com.example.spring.kafka.producer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {

    @NotNull
    private Integer isbnId;
    @NotBlank
    private String bookName;
    @NotBlank
    private String bookAuthor;

}
