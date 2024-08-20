package com.inventorymanagement.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Entity representing a product in the inventory management system. Maps to the
 * 'Product' table in the database.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
		@NamedQuery(name = "Product.findAllProductsByNameDescending", query = "select p from Product p ORDER BY p.productName DESC") })
public class Product {

	/**
	 * The unique identifier for the product. This field is mapped to the
	 * 'ProductId' column in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductId")
	/* default */int productId;

	/**
	 * The name of the product. It must not be blank. This field is mapped to the
	 * 'ProductName' column in the database.
	 */
	@NotBlank(message = "Product name cannot be blank.")
	@Column(name = "ProductName", unique = true)
	/* default */String productName;

	/**
	 * The available stock quantity of the product. It must be a non-negative
	 * integer. This field is mapped to the 'StockAvailable' column in the database.
	 */
	@NotNull(message = "Stock quantity cannot be null")
	@Min(value = 0, message = "Stock available must be greater than 0")
	@Column(name = "StockAvailable")
	/* default */int stockAvailable;

	/**
	 * The price of the product. It must be greater than 0.
	 */
	@NotNull(message = "Price cannot be null")
	@Min(value = 1, message = "Price must be greater than 0")
	/* default */float price;

	/**
	 * The unique barcode for the product. It must be exactly 8 characters long,
	 * starting with 4 uppercase letters followed by 4 digits.
	 */
	@NotBlank(message = "Bar code cannot be blank")
	@Pattern(regexp = "^[A-Z]{4}\\d{4}$", message = "Bar code must start with alphabet followed by numbers.Bar code must be exactly 8 characters")
	@Size(min = 8, max = 8, message = "Bar code must be exactly 8 characters")
	@Column(unique = true)
	/* default */String barcode;

	/**
	 * The brand associated with the product. This field is mapped to the 'brand_id'
	 * column in the database, referencing the 'brandId' column in the Brand entity.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id", nullable = false, referencedColumnName = "brandId")
	private Brand brand;

	/**
	 * The category associated with the product. This field is mapped to the
	 * 'category_id' column in the database, referencing the 'categoryId' column in
	 * the Category entity.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "categoryId")
	private Category category;

	/**
	 * The list of order details associated with the product. This field is mapped
	 * by the 'product' property in the OrderDetails entity.
	 */
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
	private List<OrderDetails> orderDetails;

	/**
	 * Default constructor for JPA.
	 */
	public Product() {
	}

	/**
	 * Constructs a new Product with the specified details.
	 * 
	 */
	public Product(final int productId, final String productName, final int stockAvailable, final float price,
			final String barcode) {
		this.productId = productId;
		this.productName = productName;
		this.stockAvailable = stockAvailable;
		this.price = price;
		this.barcode = barcode;
	}

	// Getters and Setters

	/**
	 * Gets the brand associated with the product.
	 * 
	 * @return the brand of the product
	 */
	public Brand getBrand() {
		return brand;
	}

	/**
	 * Sets the brand associated with the product.
	 * 
	 * @param brand the brand to be associated with the product
	 */
	public void setBrand(final Brand brand) {
		this.brand = brand;
	}

	/**
	 * Gets the category associated with the product.
	 * 
	 * @return the category of the product
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the category associated with the product.
	 * 
	 * @param category the category to be associated with the product
	 */
	public void setCategory(final Category category) {
		this.category = category;
	}

	/**
	 * Gets the list of order details associated with the product.
	 * 
	 * @return the list of order details
	 */
	public List<OrderDetails> getOrder() {
		return orderDetails;
	}

	/**
	 * Sets the list of order details associated with the product.
	 * 
	 * @param orderDetails the list of order details to be associated with the
	 *                     product
	 */
	public void setOrder(final List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	/**
	 * Gets the unique identifier of the product.
	 * 
	 * @return the product ID
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Sets the unique identifier of the product.
	 * 
	 * @param productId the product ID to be set
	 */
	public void setProductId(final int productId) {
		this.productId = productId;
	}

	/**
	 * Gets the name of the product.
	 * 
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the name of the product.
	 * 
	 * @param productName the product name to be set
	 */
	public void setProductName(final String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the available stock quantity of the product.
	 * 
	 * @return the available stock quantity
	 */
	public int getStockAvailable() {
		return stockAvailable;
	}

	/**
	 * Sets the available stock quantity of the product.
	 * 
	 * @param stockAvailable the stock quantity to be set
	 */
	public void setStockAvailable(final int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	/**
	 * Gets the price of the product.
	 * 
	 * @return the price of the product
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the price of the product.
	 * 
	 * @param price the price to be set
	 */
	public void setPrice(final float price) {
		this.price = price;
	}

	/**
	 * Gets the barcode of the product.
	 * 
	 * @return the barcode of the product
	 */
	public String getBarCode() {
		return barcode;
	}

	/**
	 * Sets the barcode of the product.
	 * 
	 * @param barcode the barcode to be set
	 */
	public void setBarCode(final String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", stockAvailable=" + stockAvailable
				+ ", price=" + price + ", barCode=" + barcode + "]";
	}

}
