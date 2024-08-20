package com.inventorymanagement.dao;

/**
 * Projection interface for customized product category data. This interface is
 * used to fetch category names and the count of products within each category
 * from the database.
 */
public interface ProductCategoryCustomized {

	/**
	 * Gets the category name.
	 *
	 * @return the category name
	 */
	String getCategoryName();

	/**
	 * Gets the count of products in the category.
	 *
	 * @return the product count
	 */
	int getProductCount();

}
