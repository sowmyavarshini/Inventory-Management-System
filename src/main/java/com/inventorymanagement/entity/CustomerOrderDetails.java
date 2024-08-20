package com.inventorymanagement.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/**
 * Represents a customer order detail in the inventory management system.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CustomerOrderDetails {
	/**
	 * The unique identifier for the customer order table. This field is mapped to
	 * the 'customer order id' column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CustomerOrderId")
	/* default */Integer customerOrderId;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customerId")
	private Customer customer;

	@OneToOne
	@JoinColumn(name = "order_id", nullable = false, referencedColumnName = "orderId")
	private OrderDetails order;

	@OneToOne
	@JoinColumn(name = "payment_id", nullable = false, referencedColumnName = "paymentId")
	private PaymentDetails payment;

	public CustomerOrderDetails() {
	}

	public CustomerOrderDetails(Customer customer, OrderDetails order, PaymentDetails payment) {
		this.customer = customer;
		this.order = order;
		this.payment = payment;
	}

	public Integer getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(Integer customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderDetails getOrder() {
		return order;
	}

	public void setOrder(OrderDetails order) {
		this.order = order;
	}

	public PaymentDetails getPayment() {
		return payment;
	}

	public void setPayment(PaymentDetails payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "CustomerOrderDetails [customerOrderId=" + customerOrderId + "]";
	}

}
