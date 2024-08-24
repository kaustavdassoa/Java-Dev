package com.example.simple.saga.steps.customer.service;

import com.example.simple.saga.SagaStep;
import com.example.simple.saga.dto.OrderDTO;
import com.example.simple.saga.exception.SagaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class CreateCustomerSteps implements SagaStep<OrderDTO> {

    private Customer customer;

    public CreateCustomerSteps(OrderDTO order) {
        this.customer = order.getCustomer();
    }

    public void addCustomer(Customer customer)
    {
        if(customer.fistName.equalsIgnoreCase("osama"))
            throw new SagaException("Cannot create customer");

        log.info("Customer Created : {}",customer.fistName);
    }

    public void cancelCustomer(Customer customer)
    {
        log.info("Customer {} cancelled",customer);
    }

    @Override
    public void execute(OrderDTO order) throws SagaException {
        log.info("Executing CreateCustomerSteps");
        addCustomer(order.getCustomer());
        log.info("CreateCustomerSteps Completed Successfully");
    }

    @Override
    public void compensate(OrderDTO order) throws SagaException {
        log.info("Compensation triggered for CreateCustomerSteps");
        cancelCustomer(order.getCustomer());
        log.info("CreateCustomerSteps rollback Successfully");
    }
}
