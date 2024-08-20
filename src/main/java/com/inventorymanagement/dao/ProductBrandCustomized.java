package com.inventorymanagement.dao;

/**
 * Projection interface for customized product brand data. This interface is
 * used to fetch brand names and the count of products associated with each
 * brand from the database.
 */
public interface ProductBrandCustomized {
	/**
	 * Gets the brand name.
	 *
	 * @return the brand name
	 */
	String getBrandName();

	/**
	 * Gets the count of products associated with the brand.
	 *
	 * @return the product count
	 */
	int getProductCount();

}
