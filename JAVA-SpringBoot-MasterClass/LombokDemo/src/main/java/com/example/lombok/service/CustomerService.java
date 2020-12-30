package com.example.lombok.service;

import com.example.lombok.dao.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    Customer customer;

    public void setDummyCustomer()
    {
        log.info("Inside setDummyCustomer() method");
        customer.setName("Kaustav Das");
        log.debug("customer name is set to {}","Kaustav Das");
        customer.setAge(40);
        log.debug("customer age is set to {}","40");
        customer.setAddress("G-701 Durga Petals Bangalore -560078");
        log.debug("customer address is set to {}","G-701 Durga Petals Bangalore -560078");
        log.info("Exiting setDummyCustomer() method");
    }

    public void printCustomerDetails(){
        setDummyCustomer();

    }
}
