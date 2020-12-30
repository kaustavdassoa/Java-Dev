package com.example.SpringBootJUnitDemo.repositories;

import com.example.SpringBootJUnitDemo.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CustomerRepository  extends CrudRepository<Customer, Long> {

    /*
     * Defined custom method
     */
    public ArrayList<Customer>  findByCustomerTypeID(Long customerTypeID);
}
