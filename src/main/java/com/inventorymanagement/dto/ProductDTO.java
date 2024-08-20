package com.inventorymanagement.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for representing product details. Implements
 * Serializable for object serialization.
 */
public class ProductDTO implements Serializable {

	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ID of the product.
	 */
	@Id
	private int productId;

	/**
	 * The name of the product. Must not be blank.
	 */
	@NotBlank(message = "Product name cannot be blank.")
	private String productName;

	/**
	 * The quantity of the product in stock. Must not be null and must be greater
	 * than or equal to 0.
	 */
	@NotNull(message = "Stock quantity cannot be null")
	@Min(value = 0, message = "Stock available must be greater than 0")
	private int stockAvailable;

	/**
	 * The price of the product. Must not be null and must be greater than 0.
	 */
	@NotNull(message = "Price cannot be null")
	@Min(value = 1, message = "Price must be greater than 0")
	private float price;

	/**
	 * The barcode of the product. Must be exactly 8 characters long, starting with
	 * 4 uppercase letters followed by 4 digits.
	 */
	@NotBlank(message = "Bar code cannot be blank")
	@Pattern(regexp = "^[A-Z]{4}\\d{4}$", message = "Bar code must start with alphabet followed by numbers. Bar code must be exactly 8 characters")
	@Size(min = 8, max = 8, message = "Bar code must be exactly 8 characters")
	private String barcode;

	/**
	 * The ID of the brand associated with the product. Must not be null.
	 */
	@NotNull(message = "Brand id is mandatory")
	private Integer brandId;

	/**
	 * The name of the brand associated with the product.
	 */
	private String brandName;

	/**
	 * The ID of the category associated with the product. Must not be null.
	 */
	@NotNull(message = "Category id is mandatory")
	private Integer categoryId;

	/**
	 * The name of the category associated with the product.
	 */
	private String categoryName;

	/**
	 * Default constructor.
	 */
	public ProductDTO() {
		// No-argument constructor
	}

	/**
	 * Parameterized constructor to initialize all fields.
	 *
	 */
	public ProductDTO(final int productId, final String productName, final int stockAvailable, final float price,
			final String barcode, final Integer brandId, final String brandName, final Integer categoryId,
			final String categoryName) {
		this.productId = productId;
		this.productName = productName;
		this.stockAvailable = stockAvailable;
		this.price = price;
		this.barcode = barcode;
		this.brandId = brandId;
		this.brandName = brandName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	// Getter and Setter methods
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(final int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public int getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(final int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(final float price) {
		this.price = price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(final String barcode) {
		this.barcode = barcode;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(final Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(final Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", productName=" + productName + ", stockAvailable="
				+ stockAvailable + ", price=" + price + ", barcode=" + barcode + "]";
	}

}
