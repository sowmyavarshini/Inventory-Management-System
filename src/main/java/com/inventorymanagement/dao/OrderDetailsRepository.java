package com.inventorymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entity.OrderDetails;

/**
 * Repository interface for managing {@link OrderDetails} entities. This
 * interface extends {@link JpaRepository}, providing CRUD operations for
 * {@link OrderDetails} entities.
 */
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
