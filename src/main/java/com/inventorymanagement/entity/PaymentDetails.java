package com.inventorymanagement.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a payment detail in the inventory management system.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentId")
	/* default */Integer paymentId;

	@NotNull
	@Column(name = "PaymentStatus")
	/* default */int paymentStatus;

	@Column(name = "PaymentDate", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date paymentDate;

	@NotBlank
	@Column(name = "PaymentAmount")
	/* default */float paymentAmount;

	@OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CustomerOrderDetails customerOrder;

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public float getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public CustomerOrderDetails getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrderDetails customerOrder) {
		this.customerOrder = customerOrder;
	}

	@Override
	public String toString() {
		return "PaymentDetails [paymentId=" + paymentId + ", paymentStatus=" + paymentStatus + ", paymentDate="
				+ paymentDate + ", paymentAmount=" + paymentAmount + "]";
	}

}
