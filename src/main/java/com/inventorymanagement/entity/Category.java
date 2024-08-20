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
 * Represents a category of products in the inventory management system.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category {

	/**
	 * The unique identifier for the category. This field is mapped to the
	 * 'CategoryId' column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	/* default */int categoryId;

	/**
	 * The name of the category. This field must be non-blank and unique in the
	 * database.
	 */
	@NotBlank(message = "Category name cannot be null")
	@Column(name = "CategoryName", unique = true)
	/* default */String categoryName;

	/**
	 * The list of products associated with this category. This field represents a
	 * one-to-many relationship where each category can have multiple products. The
	 * 'products' list will be managed by the cascade operations defined
	 * (CascadeType.ALL).
	 */
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;

	/**
	 * Default constructor for JPA.
	 */
	public Category() {
//		default constructor
	}

	/**
	 * Constructs a new Category with the specified details.
	 * 
	 * @param categoryId   the ID of the category
	 * @param categoryName the name of the category
	 */
	public Category(final int categoryId, final String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	// Getters and Setters

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(final List<Product> products) {
		this.products = products;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(final int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}
