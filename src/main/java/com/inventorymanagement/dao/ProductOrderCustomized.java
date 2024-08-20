package com.inventorymanagement.dao;

import java.util.Date;

/**
 * Projection interface for customized product order data. This interface is
 * used to fetch specific columns from the database rather than fetching entire
 * entities.
 */
public interface ProductOrderCustomized {
	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	String getProductName();

	/**
	 * Gets the product price.
	 *
	 * @return the product price
	 */
	float getPrice();

	/**
	 * Gets the order date.
	 *
	 * @return the order date
	 */
	Date getOrderedDate();

	/**
	 * Gets the delivery date.
	 *
	 * @return the delivery date
	 */
	Date getDeliveryDate();

}
