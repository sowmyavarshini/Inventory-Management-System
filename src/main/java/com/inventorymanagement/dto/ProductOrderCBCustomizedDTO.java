package com.inventorymanagement.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for representing product and order details. Implements
 * Serializable for object serialization.
 */
public class ProductOrderCBCustomizedDTO implements Serializable {
	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ID of the product.
	 */
	/* default */int productId;

	/**
	 * The name of the product.
	 */
	/* default */String productName;

	/**
	 * The ID of the order.
	 */
	/* default */int orderId;

	/**
	 * The quantity of the ordered product.
	 */
	/* default */int orderedQuantity;

	/**
	 * The name of the brand associated with the product.
	 */
	/* default */String brandName;

	/**
	 * The name of the category associated with the product.
	 */
	/* default */String categoryName;

	/**
	 * Default constructor.
	 */
	public ProductOrderCBCustomizedDTO() {
		// No-argument constructor
	}

	/**
	 * Parameterized constructor to initialize all fields.
	 *
	 */
	public ProductOrderCBCustomizedDTO(final int productId, final String productName, final int orderId,
			final int orderedQuantity, final String brandName, final String categoryName) {
		this.productId = productId;
		this.productName = productName;
		this.orderId = orderId;
		this.orderedQuantity = orderedQuantity;
		this.brandName = brandName;
		this.categoryName = categoryName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(final int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(final int orderId) {
		this.orderId = orderId;
	}

	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(final int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

}
