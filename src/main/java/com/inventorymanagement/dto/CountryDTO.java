package com.inventorymanagement.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class CountryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	Integer countryId;

	@NotBlank
	String countryName;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
