package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.customer.Customer;
import com.example.demo.customer.impl.CustomerImpl;

@Service
public class CustomerService implements CustomerServiceI{
	
	@Autowired
	private CustomerImpl customerImpl;

	@Override
	public void saveCustomer(Customer customer) {
		customerImpl.save(customer);
	}

}
