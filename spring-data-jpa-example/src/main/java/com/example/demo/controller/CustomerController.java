package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.customer.Customer;
import com.example.demo.customer.Person;
import com.example.demo.service.CustomerServiceI;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {
	
	@Autowired
	private CustomerServiceI customerServiceI;
	
	@GetMapping("/customer")
	public String saveCustomer() {//BindingResult bindingResult
		log.info("Inside CustomerController::saveCustomer");
		Customer cust = new Customer();
		cust.setFirstName("Test");
		cust.setSecondName("Test");
		customerServiceI.saveCustomer(cust);
		
		//testing Lombok
		Person p = new Person();
		p.setFirstName("Test");
		p.getFirstName();
		
		//bindingResult.hasErrors();
		
		return "customer-saved-successfully";
	}

}
