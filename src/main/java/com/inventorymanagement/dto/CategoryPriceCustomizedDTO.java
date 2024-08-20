package com.inventorymanagement.dto;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) for customized category price details. This class
 * is used to transfer category name and its average price information.
 */
public class CategoryPriceCustomizedDTO implements Serializable {
	/**
	 * Serial Verion UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the name of the product category. This field holds the name used
	 * to identify the category.
	 */

	/* default */String categoryName;

	/**
	 * The average price of products within this category. This field provides an
	 * average value of the prices of all products belonging to the category.
	 */
	/* default */float averagePrice;

	/**
	 * Gets the name of the category.
	 *
	 * @return the category name.
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Sets the name of the category.
	 *
	 * @param categoryName the category name.
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Gets the average price of the products in the category.
	 *
	 * @return the average price.
	 */
	public float getAveragePrice() {
		return averagePrice;
	}

	/**
	 * Sets the average price of the products in the category.
	 *
	 * @param averagePrice the average price.
	 */
	public void setAveragePrice(final float averagePrice) {
		this.averagePrice = averagePrice;
	}

}
