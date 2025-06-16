package com.example.demo.customer.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.customer.Customer;

public interface CustomerImpl extends JpaRepository<Customer, Integer> {
}
