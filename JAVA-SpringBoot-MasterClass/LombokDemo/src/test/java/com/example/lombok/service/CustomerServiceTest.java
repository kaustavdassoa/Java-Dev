package com.example.lombok.service;

import com.example.lombok.dao.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    Customer customer;

    @BeforeEach
    void setUp() {
        this.customer= new Customer();

    }

    @Test
    void setDummyCustomer() {
        assertTrue(true);
    }

    @Test
    void printCustomerDetails() {
        assertTrue(true);
    }
}