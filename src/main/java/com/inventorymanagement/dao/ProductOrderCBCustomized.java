package com.inventorymanagement.dao;

/**
 * Projection interface for customized product order data, including product,
 * order, brand, and category details. This interface is used to fetch specific
 * columns from the database rather than fetching entire entities.
 */
public interface ProductOrderCBCustomized {
	/**
	 * Gets the product ID.
	 *
	 * @return the product ID
	 */
	int getProductId();

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	String getProductName();

	/**
	 * Gets the order ID.
	 *
	 * @return the order ID
	 */
	int getOrderId();

	/**
	 * Gets the ordered quantity.
	 *
	 * @return the ordered quantity
	 */
	int getOrderedQuantity();

	/**
	 * Gets the brand name.
	 *
	 * @return the brand name
	 */
	String getBrandName();

	/**
	 * Gets the category name.
	 *
	 * @return the category name
	 */
	String getCategoryName();

}
