package com.inventorymanagement.dao;

import java.util.Calendar;
import java.util.Date;

import com.inventorymanagement.entity.OrderDetails;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/**
 * Listener class for {@link OrderDetails} entity to handle pre-persist and
 * pre-update events. This class automatically sets the ordered date and
 * calculates the delivery date when a new order is persisted.
 */
public class OrderDetailsListener {
	/**
	 * Default constructor.
	 */
	public OrderDetailsListener() {
		// Default constructor
	}

	/**
	 * Sets the ordered date to the current date and calculates the delivery date as
	 * 7 days from the ordered date before the entity is persisted.
	 *
	 * @param orderDetails the {@link OrderDetails} entity being persisted.
	 */
	@PrePersist
	public void prePersist(final OrderDetails orderDetails) {
		final Date orderedDate = new Date();
		orderDetails.setOrderedDate(orderedDate);

		// Calculate deliveryDate as 7 days from orderedDate
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(orderedDate);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		orderDetails.setDeliveryDate(calendar.getTime());
	}

	/**
	 * Placeholder for pre-update logic. Currently does nothing to preserve the
	 * existing dates during updates.
	 *
	 * @param orderDetails the {@link OrderDetails} entity being updated.
	 */
	@PreUpdate
	public void preUpdate(OrderDetails orderDetails) {
		// Do nothing to preserve the dates during updates
	}
}
