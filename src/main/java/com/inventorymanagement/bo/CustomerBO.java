package com.inventorymanagement.bo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventorymanagement.dao.CustomerRepository;
import com.inventorymanagement.entity.Customer;

/**
 * Business Object (BO) for handling operations related to customers. Provides
 * methods for managing customer records, including insertion and retrieval.
 */
@Component
public class CustomerBO {

	/**
	 * Repository instance used to perform CRUD operations on Customer entities. It
	 * is automatically injected by Spring's dependency injection mechanism.
	 */
	@Autowired
	/* default */CustomerRepository cust;

	/**
	 * Inserts a new customer into the repository.
	 * 
	 * @param customer the customer to be inserted.
	 * @return the inserted customer.
	 */
	public Customer insert(final Customer customer) {
		return cust.save(customer);
	}

	/**
	 * Finds a customer by their ID.
	 * 
	 * @param customerId the ID of the customer to be found.
	 * @return the found customer.
	 * @throws NoSuchElementException if the customer is not found.
	 */
	public Customer findCustomer(final int customerId) {
		final Optional<Customer> customer = cust.findById(customerId);
		return (customer.get());
	}

	/**
	 * Finds all customers.
	 * 
	 * @return a list of all customers.
	 */
	public List<Customer> findCustomers() {
		return cust.findAll();
	}

}
