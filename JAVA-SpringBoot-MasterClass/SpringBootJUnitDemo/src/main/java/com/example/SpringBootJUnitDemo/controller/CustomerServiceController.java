package com.example.SpringBootJUnitDemo.controller;

import com.example.SpringBootJUnitDemo.domain.Customer;
import com.example.SpringBootJUnitDemo.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping (value = "/v01")
public class CustomerServiceController {

    @Autowired
    CustomerService customerService;

    @GetMapping(path = "/getCustomerByCustID/{id}")
    public  ArrayList <Customer>  getCustomerByCustID(@PathVariable Long id)
    {
        log.info("inside getCustomerByCustID() method");
        ArrayList <Customer> customerList;
        customerList=customerService.getCustomersByCustomerType(id);


        return customerList;
    }
}
