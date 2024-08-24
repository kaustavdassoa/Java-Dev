package com.example.simple.saga.steps.account.service;

import com.example.simple.saga.SagaStep;
import com.example.simple.saga.dto.OrderDTO;
import com.example.simple.saga.exception.SagaException;

public class VerifyAccountSteps implements SagaStep<OrderDTO> {

    @Override
    public void execute(OrderDTO data) throws SagaException {
        if(data.getAccount().type.getDisplayName().equalsIgnoreCase("Non Prime"))
            throw new SagaException("Non Prime account not supported");
    }

    @Override
    public void compensate(OrderDTO data) throws SagaException {

    }
}
