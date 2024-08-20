package com.inventorymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entity.Customer;

/**
 * Repository interface for managing {@link Customer} entities. This interface
 * extends {@link JpaRepository}, providing CRUD operations for {@link Customer}
 * entities.
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	boolean existsByCustomerId(Integer customerId);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Customer findByUsername(String username);

}
