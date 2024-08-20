package com.inventorymanagement.dao;

/**
 * Interface representing a view of the Product entity with certain fields in
 * uppercase.
 */
public interface ProductUpper {
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
	 * Gets the price of the product.
	 *
	 * @return the price of the product
	 */
	float getPrice();
}
