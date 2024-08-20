package com.inventorymanagement.dao;

/**
 * Projection interface for retrieving category names and their corresponding
 * average product prices.
 */
public interface CategoryPriceCustomized {
	/**
	 * Gets the name of the category.
	 * 
	 * @return the category name.
	 */
	String getCategoryName();

	/**
	 * Gets the average price of products in the category.
	 * 
	 * @return the average price.
	 */
	float getAveragePrice();

}
