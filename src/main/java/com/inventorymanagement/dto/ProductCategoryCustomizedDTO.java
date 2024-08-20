package com.inventorymanagement.dto;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) for customized product category information. This
 * class is used to transfer category name and product count information.
 */
public class ProductCategoryCustomizedDTO implements Serializable {
	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the category. This field represents the category's name in the
	 * system.
	 */
	/* default */String categoryName;

	/**
	 * The number of products that belong to this category. This field represents
	 * the count of products associated with the category.
	 */
	/* default */int productCount;

	/**
	 * Gets the name of the product category.
	 *
	 * @return the name of the product category.
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Sets the name of the product category.
	 *
	 * @param categoryName the name of the product category.
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Gets the count of products in the category.
	 *
	 * @return the count of products in the category.
	 */
	public int getProductCount() {
		return productCount;
	}

	/**
	 * Sets the count of products in the category.
	 *
	 * @param productCount the count of products in the category.
	 */
	public void setProductCount(final int productCount) {
		this.productCount = productCount;
	}

}
