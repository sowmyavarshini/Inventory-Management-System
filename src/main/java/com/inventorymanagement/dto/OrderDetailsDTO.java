package com.inventorymanagement.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for order details. This class is used to transfer
 * order details information.
 */
public class OrderDetailsDTO implements Serializable {

	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Unique identifier for the order. This field serves as the primary key for the
	 * OrderDetails entity.
	 */
	@Id
	private int orderId;

	/**
	 * The quantity of items ordered. This field specifies how many units of the
	 * product were ordered. It cannot be null and must be greater than zero.
	 */
	@NotNull(message = "Ordered quantity cannot be null")
	@Min(value = 1, message = "Ordered quantity must be greater than 0")
	private int orderedQuantity;

	/**
	 * The date when the order was placed. This field captures the timestamp of when
	 * the order was created.
	 */
	private Date orderedDate;

	/**
	 * The date when the order is expected to be delivered. This field represents
	 * the anticipated delivery date for the order.
	 */
	private Date deliveryDate;

	/**
	 * The ID of the product associated with the order. This field must not be null
	 * and is used to link the order to a specific product.
	 */
	@NotNull(message = "Product is is mandatory.")
	private Integer productId;

	/**
	 * The name of the product associated with the order. This field represents the
	 * name of the product being ordered.
	 */
	private String productName;

	/**
	 * Gets the name of the product associated with the order.
	 *
	 * @return the name of the product.
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the name of the product associated with the order.
	 *
	 * @param productName the name of the product.
	 */
	public void setProductName(final String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the unique identifier for the order.
	 *
	 * @return the order ID.
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * Sets the unique identifier for the order.
	 *
	 * @param orderId the order ID.
	 */
	public void setOrderId(final int orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the quantity of items ordered.
	 *
	 * @return the ordered quantity.
	 */
	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	/**
	 * Sets the quantity of items ordered.
	 *
	 * @param orderedQuantity the ordered quantity.
	 */
	public void setOrderedQuantity(final int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	/**
	 * Gets the date when the order was placed.
	 *
	 * @return the ordered date.
	 */
	public Date getOrderedDate() {
		return orderedDate;
	}

	/**
	 * Sets the date when the order was placed.
	 *
	 * @param orderedDate the ordered date.
	 */
	public void setOrderedDate(final Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	/**
	 * Gets the date when the order is expected to be delivered.
	 *
	 * @return the delivery date.
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * Sets the date when the order is expected to be delivered.
	 *
	 * @param deliveryDate the delivery date.
	 */
	public void setDeliveryDate(final Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * Gets the ID of the product associated with the order.
	 *
	 * @return the product ID.
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * Sets the ID of the product associated with the order.
	 *
	 * @param productId the product ID.
	 */
	public void setProductId(final Integer productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "OrderDetailsDTO [orderId=" + orderId + ", orderedQuantity=" + orderedQuantity + ", orderedDate="
				+ orderedDate + ", deliveryDate=" + deliveryDate + "]";
	}

}
