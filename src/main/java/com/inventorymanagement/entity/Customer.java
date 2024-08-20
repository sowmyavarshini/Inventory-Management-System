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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Represents a customer in the inventory management system.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {

	/**
	 * The unique identifier for the customer. This field is mapped to the
	 * 'CustomerId' column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CustomerId")
	/* default */Integer customerId;

	/**
	 * The username of the customer. This field must be non-blank and unique. It is
	 * mapped to a column with a unique constraint in the database.
	 */
	@NotBlank(message = "User name cannot be blank.")
	@Column(unique = true)
	/* default */String username;

	/**
	 * The password for the customer. This field is required and mapped to the
	 * 'UserPassword' column in the database.
	 */
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,}$", message = "Password must contain at least 4 characters, including 1 uppercase letter, 1 lowercase letter, 1 number, and 1 special character.")
	@NotBlank(message = "User password cannot be blank.")
	@Column(name = "UserPassword")
	/* default */String userpassword;

	/**
	 * The email address of the customer. This field must be non-blank.
	 */
	@NotBlank(message = "Email cannot be blank.")
	@Email(message = "Invalid email format.")
	@Column(unique = true)
	/* default */String email;

	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false, referencedColumnName = "cityId")
	private City city;

	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false, referencedColumnName = "stateId")
	private State state;

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false, referencedColumnName = "countryId")
	private Country country;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CustomerOrderDetails> customerOrders;

	/**
	 * Default constructor for JPA.
	 */
	public Customer() {
	}

	/**
	 * Constructs a new Customer with the specified details.
	 * 
	 */
	public Customer(final Integer customerId, final String username, final String userpassword, final String email,
			final City city, final State state, final Country country) {
		this.customerId = customerId;
		this.username = username;
		this.userpassword = userpassword;
		this.email = email;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	// Getters and Setters

	public Integer getCustomerId() {
		return customerId;
	}

	public List<CustomerOrderDetails> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(List<CustomerOrderDetails> customerOrders) {
		this.customerOrders = customerOrders;
	}

	public void setCustomerId(final Integer customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(final String userpassword) {
		this.userpassword = userpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", username=" + username + ", userpassword=" + userpassword
				+ ", email=" + email + "]";
	}

}
