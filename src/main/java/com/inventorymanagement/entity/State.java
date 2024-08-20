package com.inventorymanagement.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

/**
 * Entity representing a product in the inventory management system. Maps to the
 * 'Country' table in the database.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class State {

	/**
	 * The unique identifier for the country. This field is mapped to the 'StateId'
	 * column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StateId")
	/* default */Integer stateId;

	/**
	 * The name of the state. This field must be non-blank and unique. It is mapped
	 * to a column with a unique constraint in the database.
	 */
	@NotBlank
	@Column(name = "StateName", unique = true)
	/* default */String stateName;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Customer> customers;

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", stateName=" + stateName + "]";
	}

}