package com.example.SpringBootJUnitDemo.repository;

import com.example.SpringBootJUnitDemo.domain.Customer;
import com.example.SpringBootJUnitDemo.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class CustomerRepositoryTest {

	@Autowired
	CustomerRepository customerRepository;

	final static String CUSTOMER_NAME="Roberta";
	final static Long CUSTOMER_ID=3L;
	final static Long CUSTOMER_TYPEID=73L;


	@Test
	public void testCustomerRepository_findById_Positive() {
		Optional<Customer> customer=customerRepository.findById(3L);


		//Assert
		assertEquals(CUSTOMER_ID,customer.get().getCustomerID());
		assertEquals(CUSTOMER_NAME,customer.get().getCustomerName());
		assertEquals(CUSTOMER_TYPEID,customer.get().getCustomerTypeID());

	}

	@Test
	public void testCustomerRepository_findById_Negative() {
		Optional<Customer> customer=customerRepository.findById(4L);

		//Assert
		assertTrue(customer.isEmpty());

	}




	@Test
	public void testCustomerRepository_findByCustomerTypeID_Functions() {
		ArrayList<Customer> customerArrayList=customerRepository.findByCustomerTypeID(73L);

		//Assert
		assertEquals(1,customerArrayList.size());
	}

	@Test
	public void testCustomerRepository_autoIncrement_Functions(){


		Customer customer=new Customer();
		customer.setCustomerName(ArgumentMatchers.anyString());
		customer.setCustomerTypeID(this.CUSTOMER_TYPEID);

		long intialCount =customerRepository.count();
		Customer customerObj=customerRepository.save(customer);
		long afterSaveCount =customerRepository.count();

		//Assert
		assertTrue(intialCount<afterSaveCount);
		assertNotNull(customerObj);
		assertNotNull(customerObj.getCustomerID());


	}

}
