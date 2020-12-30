package com.example.SpringBootJUnitDemo.services;

import com.example.SpringBootJUnitDemo.domain.Customer;
import com.example.SpringBootJUnitDemo.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class CustomerService {



    CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public ArrayList<Customer> getCustomersByCustomerType(Long customerTypeID)
    {

      log.info("Inside customer service method");
      ArrayList<Customer> customerList=new ArrayList<>();

      customerList=customerRepo.findByCustomerTypeID(customerTypeID);
      int count=0;
      for(Customer customer: customerList)
      {
          ++count;
          log.info("Customer Details [{}] : {}",count,customer.toString());
      }


      return customerList;
    }
}
