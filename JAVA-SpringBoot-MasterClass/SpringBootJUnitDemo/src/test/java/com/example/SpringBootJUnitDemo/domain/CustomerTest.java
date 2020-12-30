package com.example.SpringBootJUnitDemo.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {

    Customer customer;
    final static Long CUSTOMER_ID=1L;
    final static String CUSTOMER_NAME="Alex";
    final static Long CUSTOMER_TYPEID=73L;

    @BeforeEach
    void setUp() {
        this.customer=new Customer();
    }

    @Test
    public void validateCustomerGetterANDSetter() {
        this.customer.setCustomerID(this.CUSTOMER_ID);
        this.customer.setCustomerName(this.CUSTOMER_NAME);
        this.customer.setCustomerTypeID(this.CUSTOMER_TYPEID);

        assertEquals(this.CUSTOMER_ID,this.customer.getCustomerID());
        assertEquals(this.CUSTOMER_NAME,this.customer.getCustomerName());
        assertEquals(this.CUSTOMER_TYPEID,this.customer.getCustomerTypeID());
    }

    @Test
    public void validateCustomerToString() {
        this.customer.setCustomerID(this.CUSTOMER_ID);
        this.customer.setCustomerName(this.CUSTOMER_NAME);
        this.customer.setCustomerTypeID(this.CUSTOMER_TYPEID);

        assertTrue(this.customer.toString().length()>0);
        assertEquals("Customer(customerID=1, customerName=Alex, customerTypeID=73)",this.customer.toString());

    }


}