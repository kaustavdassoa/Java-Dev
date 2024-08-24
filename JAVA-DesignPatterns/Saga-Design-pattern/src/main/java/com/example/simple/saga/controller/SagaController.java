package com.example.simple.saga.controller;

import com.example.simple.saga.dto.OrderDTO;
import com.example.simple.saga.orchestrator.CreateOrderProcess;
import com.example.simple.saga.steps.customer.service.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SagaController {


    @Autowired
    CreateOrderProcess createOrder;

    @PostMapping("/onlinePayment")
    public void onlinePayment(@RequestBody OrderDTO order)
    {
        log.info("Order :{}",order);
        createOrder.createOrder(order);
    }

}
