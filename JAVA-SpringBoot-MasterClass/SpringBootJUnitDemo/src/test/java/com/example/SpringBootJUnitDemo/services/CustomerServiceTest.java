package com.example.SpringBootJUnitDemo.services;

import com.example.SpringBootJUnitDemo.domain.Customer;
import com.example.SpringBootJUnitDemo.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class CustomerServiceTest {


    final static Long CUSTOMER_ID=1L;
    final static String CUSTOMER_NAME="Alex";
    final static Long CUSTOMER_TYPEID=73L;

    CustomerService customerSerice;


    @Mock
    CustomerRepository customerRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerSerice=new CustomerService(customerRepository);

    }

    @Test
    void testGetCustomersByCustomerType() {

        //Given
        ArrayList<Customer> customerList=new ArrayList<>();
        ArrayList<Customer> returnedCustomerList;
        Customer customer=new Customer();
        customer.setCustomerID(this.CUSTOMER_ID);
        customer.setCustomerName(this.CUSTOMER_NAME);
        customer.setCustomerTypeID(this.CUSTOMER_TYPEID);
        customerList.add(customer);
        when(customerSerice.getCustomersByCustomerType(CUSTOMER_TYPEID))
                .thenReturn(customerList);

        //When
        returnedCustomerList=customerSerice.getCustomersByCustomerType(CUSTOMER_TYPEID);

        //Given
        verify(customerRepository,times(1)).findByCustomerTypeID(anyLong());

    }
}