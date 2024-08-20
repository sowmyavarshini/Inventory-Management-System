package com.inventorymanagement.dao;

//ProjectionInterface
/**
 * Projection interface for customized product data. This interface is used to
 * fetch specific columns from the database rather than fetching entire
 * entities.
 */
public interface ProductCustomized {

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

}
