package com.inventorymanagement.dto;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) for customized product brand information. This
 * class is used to transfer brand name and product count information.
 */
public class ProductBrandCustomizedDTO implements Serializable {
	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the brand. This field represents the brand's name in the system.
	 */
	/* default */String brandName;

	/**
	 * The number of products associated with this brand. This field represents the
	 * count of products under this brand.
	 */
	/* default */int productCount;

	/**
	 * Gets the name of the product brand.
	 *
	 * @return the name of the product brand.
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * Sets the name of the product brand.
	 *
	 * @param brandName the name of the product brand.
	 */
	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}

	/**
	 * Gets the count of products in the brand.
	 *
	 * @return the count of products in the brand.
	 */
	public int getProductCount() {
		return productCount;
	}

	/**
	 * Sets the count of products in the brand.
	 *
	 * @param productCount the count of products in the brand.
	 */
	public void setProductCount(final int productCount) {
		this.productCount = productCount;
	}

}
