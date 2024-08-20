package com.inventorymanagement.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a brand of products in the inventory management system.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Brand {
	/**
	 * The unique identifier for the brand. This field is mapped to the 'BrandId'
	 * column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BrandId")
	/* default */int brandId;

	/**
	 * The name of the brand. This field must be non-blank and unique in the
	 * database.
	 */
	@NotBlank(message = "Brand name cannot be blank")
	@Column(name = "BrandName", unique = true)
	/* default */String brandName;

	/**
	 * The list of products associated with this brand. This field represents a
	 * one-to-many relationship where each brand can have multiple products. The
	 * 'products' list will be managed by the cascade operations defined
	 * (CascadeType.ALL).
	 */
	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Default constructor for JPA.
	 */
	public Brand() {
	}

	/**
	 * Constructs a new Brand with the specified details.
	 * 
	 * @param brandId   the ID of the brand
	 * @param brandName the name of the brand
	 */
	public Brand(final int brandId, final String brandName) {
		this.brandId = brandId;
		this.brandName = brandName;
	}

	// Getters and Setters
	public void setProducts(final List<Product> products) {
		this.products = products;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(final int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}

	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", brandName=" + brandName + "]";
	}

}
