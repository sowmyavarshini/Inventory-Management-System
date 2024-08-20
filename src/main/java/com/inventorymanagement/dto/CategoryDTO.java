package com.inventorymanagement.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for category details. This class is used to
 * transfer category information.
 */
public class CategoryDTO implements Serializable {

	/**
	 * Serial Version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the unique identifier for a category. This field is used to
	 * uniquely identify each category in the system.
	 */

	@Id
	/* default */int categoryId;

	/**
	 * The name of the category. This field cannot be blank and is used to describe
	 * or identify the category.
	 */
	@NotBlank(message = "Category name cannot be null")
	/* default */String categoryName;

	/**
	 * Gets the unique identifier for the category.
	 *
	 * @return the category ID.
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * Sets the unique identifier for the category.
	 *
	 * @param categoryId the category ID.
	 */
	public void setCategoryId(final int categoryId) {
		this.categoryId = categoryId;
	}

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

	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}
