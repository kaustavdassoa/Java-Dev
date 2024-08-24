package com.example.simple.saga.orchestrator;

import com.example.simple.saga.SagaOrchestrator;
import com.example.simple.saga.SagaStep;
import com.example.simple.saga.dto.OrderDTO;
import com.example.simple.saga.steps.customer.service.CreateCustomerSteps;
import com.example.simple.saga.steps.customer.service.Customer;
import com.example.simple.saga.steps.payments.service.Payments;
import com.example.simple.saga.steps.payments.service.ProcessPaymentSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CreateOrderProcess {


    public void createOrder(OrderDTO order)
    {
        List<SagaStep<OrderDTO>> steps = Arrays.asList(
                new CreateCustomerSteps(order),
                new ProcessPaymentSteps(order)
        );
        SagaOrchestrator<OrderDTO> orchestrator=new SagaOrchestrator<OrderDTO>(steps);

        orchestrator.execute(order);
    }
}
