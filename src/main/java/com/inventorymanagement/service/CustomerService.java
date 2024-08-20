package com.inventorymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventorymanagement.bo.CustomerBO;
import com.inventorymanagement.dao.CustomerRepository;
import com.inventorymanagement.entity.Customer;

/**
 * Service class responsible for handling customer-related business logic. It is
 * marked as a Spring component to be managed by the Spring container.
 */
@Component
public class CustomerService {

	/**
	 * Business object (BO) used for customer operations. Injected by Spring through
	 * the @Autowired annotation.
	 */
	@Autowired
	/* default */CustomerBO customerBO = null;

	@Autowired
	/* default */CustomerRepository customerRepository = null;

	/**
	 * Default Constructor
	 */
	public CustomerService() {
		super();
	}

	public Customer insert(Customer customer) {
		return customerBO.insert(customer);
	}

//	public void fetchById(int id) {
//		customerBO.findCustomer(id);
//	}
//
//	public void fetchAll() {
//		customerBO.fetchAll();
//
//	}
//
//	public void update() {
//		customerBO.update();
//
//	}
	public boolean customerExists(Integer customerId) {
		return customerRepository.existsByCustomerId(customerId);
	}

	public boolean userExists(String username) {
		return customerRepository.existsByUsername(username);
	}

	public boolean emailExists(String email) {
		return customerRepository.existsByEmail(email);
	}

}
