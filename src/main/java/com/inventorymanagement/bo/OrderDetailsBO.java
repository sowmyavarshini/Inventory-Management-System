package com.inventorymanagement.bo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.inventorymanagement.dao.OrderDetailsRepository;
import com.inventorymanagement.entity.OrderDetails;
import com.inventorymanagement.exception.ResourceNotFoundException;

/**
 * Business Object (BO) for handling operations related to order details.
 * Provides methods for managing order details, including insertion and
 * retrieval.
 */
@Component
public class OrderDetailsBO {

	/**
	 * Repository instance used to perform CRUD operations on OrderDetails entities.
	 * It is automatically injected by Spring's dependency injection mechanism.
	 */
	@Autowired
	private OrderDetailsRepository orderDetails;

	/**
	 * Constructor for OrderDetailsBO.
	 * 
	 * @param orderDetailsRepository the order details repository to be injected.
	 */
	public OrderDetailsBO(final OrderDetailsRepository orderDetails) {
		this.orderDetails = orderDetails;
	}

	/**
	 * Inserts a new order detail into the repository.
	 * 
	 * @param order the order detail to be inserted.
	 * @return the inserted order detail.
	 * @throws DataIntegrityViolationException if there is a constraint violation.
	 */

	public OrderDetails insert(final OrderDetails order) {
		try {
			return orderDetails.save(order);
		} catch (DataIntegrityViolationException e) {
			throw e;
		}
	}

	/**
	 * Finds an order detail by its ID.
	 * 
	 * @param orderId the ID of the order detail to be found.
	 * @return the found order detail.
	 * @throws ResourceNotFoundException if the order detail is not found.
	 */
	public OrderDetails findOrderDetail(final int orderId) throws ResourceNotFoundException {
		try {
			final Optional<OrderDetails> order = orderDetails.findById(orderId);
			return order
					.orElseThrow(() -> new ResourceNotFoundException("Order details not found with ID: " + orderId));
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds all order details.
	 * 
	 * @return a list of all order details.
	 */
	public List<OrderDetails> findOrderDetails() {
		try {
			return orderDetails.findAll();
		} catch (DataAccessException e) {
			throw e;
		}
	}

}
