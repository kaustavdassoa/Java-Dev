package com.example.simple.saga.steps.payments.service;

import com.example.simple.saga.SagaStep;
import com.example.simple.saga.dto.OrderDTO;
import com.example.simple.saga.exception.SagaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class ProcessPaymentSteps implements SagaStep<OrderDTO> {

    private OrderDTO order;

    public ProcessPaymentSteps(OrderDTO order) {
            this.order=order;

    }

    public void verifyPayments(OrderDTO order)
    {
        if(order.getPayments().type == PaymentType.PAYMENT_GATEWAY)
            throw new SagaException("Payment Gateway payments not accepted");

        log.info("Verified Payments");
    }

    public void reversePayment(OrderDTO order)
    {
        log.info("Reverse [{}] $ successfully",order.getPayments().amount);
    }

    @Override
    public void execute(OrderDTO order) throws SagaException {
        log.info("Executing ProcessPaymentSteps");
        verifyPayments(order);
        log.info("ProcessPaymentSteps Completed Successfully");

    }

    @Override
    public void compensate(OrderDTO order) throws SagaException {
        log.info("Compensation triggered for ProcessPaymentSteps");
        reversePayment(order);
        log.info("ProcessPaymentSteps rollback Successfully");
    }
}
