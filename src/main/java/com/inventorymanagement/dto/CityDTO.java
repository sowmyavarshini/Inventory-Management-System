package com.inventorymanagement.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class CityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	Integer cityId;

	@NotBlank
	String cityName;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
