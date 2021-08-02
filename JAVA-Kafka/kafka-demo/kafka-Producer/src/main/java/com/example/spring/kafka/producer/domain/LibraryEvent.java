package com.example.spring.kafka.producer.domain;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LibraryEvent {
    private Integer libraryEventId;
    private LibraryEvenType libraryEvenType;
    @NotNull
    @Valid
    private Book book;

}
