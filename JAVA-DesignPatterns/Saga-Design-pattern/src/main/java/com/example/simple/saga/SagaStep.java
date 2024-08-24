package com.example.simple.saga;

import com.example.simple.saga.exception.SagaException;

public interface SagaStep <T>{

    public void execute(T data) throws SagaException;
    public void compensate(T data) throws SagaException;
}
