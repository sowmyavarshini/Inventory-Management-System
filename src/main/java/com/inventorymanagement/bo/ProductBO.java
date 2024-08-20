package com.inventorymanagement.bo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.inventorymanagement.dao.CategoryPriceCustomized;
import com.inventorymanagement.dao.CustomerOrderReportCustomized;
import com.inventorymanagement.dao.ProductBrandCustomized;
import com.inventorymanagement.dao.ProductCategoryCustomized;
import com.inventorymanagement.dao.ProductCustomized;
import com.inventorymanagement.dao.ProductOrderCBCustomized;
import com.inventorymanagement.dao.ProductOrderCustomized;
import com.inventorymanagement.dao.ProductRepository;
import com.inventorymanagement.dao.ProductUpper;
import com.inventorymanagement.dao.SalesReportCustomized;
import com.inventorymanagement.dto.OrderDetailsDTO;
import com.inventorymanagement.entity.OrderDetails;
import com.inventorymanagement.entity.Product;
import com.inventorymanagement.exception.InsufficientStockException;
import com.inventorymanagement.exception.OutOfStockException;
import com.inventorymanagement.exception.ResourceNotFoundException;

/**
 * Business Object (BO) for handling operations related to products. Provides
 * methods for product management, including stock validation and adjustment.
 */
@Component
public class ProductBO {

	/**
	 * Logger instance that helps in recording log messages for the ProductBO class.
	 * It uses SLF4J with Logback as the implementation.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ProductBO.class);

	/**
	 * Repository instance used to perform CRUD operations on Product entities. It
	 * is automatically injected by Spring's dependency injection mechanism.
	 */
	@Autowired
	/* default */ProductRepository productRepository;

	/**
	 * Constructor for ProductBO.
	 * 
	 * @param productRepository the product repository to be injected.
	 */
	public ProductBO(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Inserts a new product into the repository.
	 * 
	 * @param product the product to be inserted.
	 * @return the inserted product.
	 * @throws DataIntegrityViolationException if there is a constraint violation.
	 */
	public Product insert(final Product product) {
		try {
			return productRepository.save(product);
		} catch (DataIntegrityViolationException e) {
			throw e;
		}
	}

	/**
	 * Finds a product by its ID.
	 * 
	 * @param productId the ID of the product to be found.
	 * @return the found product.
	 * @throws ResourceNotFoundException if the product is not found.
	 */
	public Product findProduct(final int productId) throws ResourceNotFoundException {
		try {
			final Optional<Product> product = productRepository.findById(productId);
			return product.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
		} catch (DataAccessException e) {
			throw e;
		}

	}

	/**
	 * Finds all products.
	 * 
	 * @return a list of all products.
	 */
	public List<Product> findProducts() {
		try {
			return productRepository.findAll();
		} catch (DataAccessException e) {
			throw e;
		}
	}

//	Queries
	/**
	 * Finds products by their ID.
	 * 
	 * @param productId the ID of the products to be found.
	 * @return a list of products greater than the specified ID.
	 */
	public List<Product> findProductsById(final int productId) {
		try {
			return productRepository.findProductById(productId);
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds products by their name.
	 * 
	 * @param productName the name of the products to be found.
	 * @return a list of products with the specified name.
	 */
	public List<Product> findProductNames(final String productName) {
		try {
			return productRepository.findByName(productName);
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds products by their price with customized information.
	 * 
	 * @param price the price to filter products.
	 * @return a list of customized product information.
	 */
	public List<ProductCustomized> findProductByPriceCustomized(final float price) {
		try {
			return productRepository.findProductByPriceCustomized(price);
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds all products ordered by name in descending order.
	 * 
	 * @return a list of products ordered by name in descending order.
	 */
	public List<Product> getAllProductsOrderedByNameDescending() {
		try {
			return productRepository.findAllProductsByNameDescending();
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds products along with their order details.
	 * 
	 * @return a list of products with order details.
	 */
	public List<Product> findProductsAndOrderDetails() {
		try {
			return productRepository.findProductsAndOrderDetails();
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds products with orders left.
	 * 
	 * @return a list of products with orders left.
	 */
	public List<Product> findProductWithOrdersLeft() {
		try {
			return productRepository.findProductWithOrdersLeft();
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds products by order details with customized information.
	 * 
	 * @param price the price to filter products.
	 * @return a list of customized product order information.
	 */
	public List<ProductOrderCustomized> findByProductOrderCustomized(final float price) {
		try {
			return productRepository.findByProductOrderCustomized(price);
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds product category count with customized information.
	 * 
	 * @return a list of product category count information.
	 */
	public List<ProductCategoryCustomized> findProductsCategoryCount() {
		try {
			return productRepository.findProductsCategoryCount();
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds average price by category with customized information.
	 * 
	 * @return a list of average price by category information.
	 */
	public List<CategoryPriceCustomized> findAvergePriceByCategory() {
		try {
			return productRepository.findAvergePriceByCategory();
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds product names in uppercase with customized information.
	 * 
	 * @return a list of products with names in uppercase.
	 */
	public List<ProductUpper> findProductNamesUppercase() {
		try {
			return productRepository.findProductNamesUppercase();

		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds product count by brand with customized information.
	 * 
	 * @param name         the brand name to filter products.
	 * @param productCount the product count to filter.
	 * @return a list of product count by brand information.
	 */
	public List<ProductBrandCustomized> findProductCountByBrand(final String name, final int productCount) {
		try {
			return productRepository.findProductCountByBrand(name, productCount);

		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds product order details with customized information.
	 * 
	 * @return a list of product order details.
	 */
	public List<ProductOrderCBCustomized> findProductOrderDetails() {
		try {
			return productRepository.findProductOrderDetails();

		} catch (DataAccessException e) {
			throw e;
		}
	}

	public List<SalesReportCustomized> generateSalesReport() {
		try {
			return productRepository.generateSalesReport();

		} catch (DataAccessException e) {
			throw e;
		}
	}

	public List<CustomerOrderReportCustomized> generateCustomerOrderReport(int customerId) {
		try {
			return productRepository.generateCustomerOrderReport(customerId);

		} catch (DataAccessException e) {
			throw e;
		}
	}

	public List<Product> findProductsByBrandName(String brandName) {
		try {
			return productRepository.findProductsByBrandName(brandName);

		} catch (DataAccessException e) {
			throw e;
		}
	}

	public List<Product> findProductsByCategoryName(String categoryName) {
		try {
			return productRepository.findProductsByCategoryName(categoryName);

		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Validates the availability of the product stock.
	 * 
	 * @param product           the product to be checked.
	 * @param requestedQuantity the quantity requested.
	 * @throws OutOfStockException        if the product is out of stock.
	 * @throws InsufficientStockException if there is not enough stock available.
	 */
	public void validateProductAvailability(final Product product, final int requestedQuantity)
			throws IllegalArgumentException {
		if (product.getStockAvailable() <= 0) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Insufficient stock for product: " + product.getProductName());
			}
			throw new OutOfStockException("Product " + product.getProductName() + " is out of stock.");
		}
		if (product.getStockAvailable() < requestedQuantity) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Out of stock for product: " + product.getProductName());
			}
			throw new InsufficientStockException("Insufficient stock for product: " + product.getProductName());
		}
	}

	/**
	 * Reduces the stock quantity of the product.
	 * 
	 * @param product  the product whose stock will be reduced.
	 * @param quantity the quantity to be reduced.
	 * @throws InsufficientStockException if there is not enough stock available.
	 * @throws OutOfStockException        if the product is out of stock.
	 */
	public void reduceQuantity(final Product product, final int quantity)
			throws InsufficientStockException, OutOfStockException {
		validateProductAvailability(product, quantity);
		product.setStockAvailable(product.getStockAvailable() - quantity);
	}

	/**
	 * Adjusts the stock quantity of the product based on old and new quantities.
	 * 
	 * @param product     the product whose stock will be adjusted.
	 * @param oldQuantity the old quantity of the product.
	 * @param newQuantity the new quantity of the product.
	 * @throws InsufficientStockException if there is not enough stock available.
	 * @throws OutOfStockException        if the product is out of stock.
	 */
	public void adjustStock(final Product product, final int oldQuantity, final int newQuantity)
			throws InsufficientStockException, OutOfStockException {
		final int difference = newQuantity - oldQuantity;
		if (difference > 0) {
			validateProductAvailability(product, difference);
		}
		product.setStockAvailable(product.getStockAvailable() - difference);
	}

	/**
	 * Updates the existing order details with new information from the DTO.
	 * 
	 * @param existingOrder   the existing order to be updated.
	 * @param orderDetailsDTO the DTO containing new order details.
	 * @param product         the product associated with the order.
	 */
	public void updateOrderDetails(final OrderDetails existingOrder, final OrderDetailsDTO orderDetailsDTO,
			final Product product) {
		existingOrder.setOrderedQuantity(orderDetailsDTO.getOrderedQuantity());
		existingOrder.setProduct(product);
	}

}
