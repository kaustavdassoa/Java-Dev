package com.example.lombok.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
public class Customer {

	public String name;
	public int age;
	public String address;
	
	public Customer()
	{
		
	}
	
}
