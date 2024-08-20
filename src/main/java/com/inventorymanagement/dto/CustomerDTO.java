package com.inventorymanagement.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CustomerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int customerId;
	@NotBlank(message = "User name cannot be blank.")
	private String userName;
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,}$", message = "Password must contain at least 4 characters, including 1 uppercase letter, 1 lowercase letter, 1 number, and 1 special character.")
	@NotBlank(message = "User password cannot be blank.")
	private String userPassword;
	@NotBlank(message = "Email cannot be blank.")
	@Email(message = "Invalid email format.")
	private String email;
	private String cityName;
	private String stateName;
	private String countryName;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
