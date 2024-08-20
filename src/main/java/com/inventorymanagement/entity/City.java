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
public class City {

	/**
	 * The unique identifier for the country. This field is mapped to the 'CityId'
	 * column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CityId")
	/* default */Integer cityId;

	/**
	 * The name of the city. This field must be non-blank and unique. It is mapped
	 * to a column with a unique constraint in the database.
	 */
	@NotBlank
	@Column(name = "CityName", unique = true)
	/* default */String cityName;

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Customer> customers;

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

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + "]";
	}

}
