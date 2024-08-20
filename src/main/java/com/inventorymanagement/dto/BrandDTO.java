package com.inventorymanagement.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for brand details. This class is used to transfer
 * brand information.
 */
public class BrandDTO implements Serializable {

	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the unique identifier for a brand. This field is used to uniquely
	 * identify each brand in the system.
	 */
	@Id
	/* default */int brandId;

	/**
	 * The name of the brand. This field cannot be blank and is used to describe or
	 * identify the brand.
	 */
	@NotBlank(message = "Brand name cannot be blank")
	/* default */String brandName;

	/**
	 * Gets the unique identifier for the brand.
	 *
	 * @return the brand ID.
	 */
	public int getBrandId() {
		return brandId;
	}

	/**
	 * Sets the unique identifier for the brand.
	 *
	 * @param brandId the brand ID.
	 */
	public void setBrandId(final int brandId) {
		this.brandId = brandId;
	}

	/**
	 * Gets the name of the brand.
	 *
	 * @return the brand name.
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * Sets the name of the brand.
	 *
	 * @param brandName the brand name.
	 */
	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}

	/**
	 * Returns a string representation of the BrandDTO.
	 *
	 * @return a string representation of the BrandDTO.
	 */
	@Override
	public String toString() {
		return "BrandDTO [brandId=" + brandId + ", brandName=" + brandName + "]";
	}

}
