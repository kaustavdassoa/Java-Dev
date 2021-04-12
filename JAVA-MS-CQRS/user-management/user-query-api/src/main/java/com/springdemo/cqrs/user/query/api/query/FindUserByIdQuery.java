package com.springdemo.cqrs.user.query.api.query;

import lombok.Data;

@Data
public class FindUserByIdQuery {
    private String id;

}
