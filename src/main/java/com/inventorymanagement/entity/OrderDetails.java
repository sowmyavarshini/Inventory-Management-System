package com.inventorymanagement.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.inventorymanagement.dao.OrderDetailsListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Entity representing a order detail in the inventory management system. Maps
 * to the 'OrderDetail' table in the database.
 */
@Entity
@EntityListeners({ AuditingEntityListener.class, OrderDetailsListener.class })
public class OrderDetails {
	/**
	 * The unique identifier for the order. This field is mapped to the 'OrderId'
	 * column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	/* default */int orderId;

	/**
	 * The quantity of the product ordered. This field must be a positive integer
	 * and is mapped to the 'OrderedQuantity' column in the database.
	 */
	@NotNull(message = "Ordered quantity cannot be null")
	@Min(value = 1, message = "Ordered quantity must be greater than 0")
	@Column(name = "OrderedQuantity")
	/* default */int orderedQuantity;

	/**
	 * The date and time when the order was placed. This field is mapped to the
	 * 'OrderedDate' column in the database and is not updatable. The `@CreatedDate`
	 * annotation indicates that this field should be automatically populated with
	 * the creation timestamp.
	 */
	@Column(name = "OrderedDate", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date orderedDate;

	/**
	 * The date and time when the order is expected to be delivered. This field is
	 * mapped to the 'DeliveryDate' column in the database and is not updatable.
	 */
	@Column(name = "DeliveryDate", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;

	/**
	 * The product associated with the order details. This field is mapped to the
	 * 'ProductId' column in the database, referencing the 'productId' column in the
	 * Product entity. The `@ManyToOne` annotation indicates that this is a
	 * many-to-one relationship, where each order detail is associated with one
	 * product. The `fetch = FetchType.EAGER` indicates that the associated product
	 * should be loaded eagerly.
	 */
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ProductId", nullable = false, referencedColumnName = "productId")
	/* default */Product product;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CustomerOrderDetails customerOrder;

	public CustomerOrderDetails getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrderDetails customerOrder) {
		this.customerOrder = customerOrder;
	}

	/**
	 * Default constructor for JPA.
	 */
	public OrderDetails() {
	}

	/**
	 * Constructs a new OrderDetails with the specified details.
	 * 
	 * @param orderId         the order ID
	 * @param orderedQuantity the ordered quantity
	 * @param product         the product associated with the order
	 */
	public OrderDetails(final int orderId, final int orderedQuantity, final Product product) {
		this.orderId = orderId;
		this.orderedQuantity = orderedQuantity;
		this.product = product;
	}

	// Getters and Setters

	public Product getProduct() {
		return product;
	}

	public void setProduct(final Product product) {
		this.product = product;
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

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(final Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(final Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", orderedQuantity=" + orderedQuantity + ", orderedDate="
				+ orderedDate + ", deliveryDate=" + deliveryDate + "]";
	}

}
