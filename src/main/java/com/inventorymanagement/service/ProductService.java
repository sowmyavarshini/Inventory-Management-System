package com.inventorymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventorymanagement.bo.OrderDetailsBO;
import com.inventorymanagement.bo.ProductBO;
import com.inventorymanagement.dao.BrandRepository;
import com.inventorymanagement.dao.CategoryPriceCustomized;
import com.inventorymanagement.dao.CategoryRepository;
import com.inventorymanagement.dao.CustomerOrderReportCustomized;
import com.inventorymanagement.dao.ProductBrandCustomized;
import com.inventorymanagement.dao.ProductCategoryCustomized;
import com.inventorymanagement.dao.ProductCustomized;
import com.inventorymanagement.dao.ProductOrderCBCustomized;
import com.inventorymanagement.dao.ProductOrderCustomized;
import com.inventorymanagement.dao.ProductRepository;
import com.inventorymanagement.dao.ProductUpper;
import com.inventorymanagement.dao.SalesReportCustomized;
import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.entity.Product;
import com.inventorymanagement.exception.ResourceNotFoundException;

/**
 * Service class responsible for handling product-related operations. It
 * interacts with the ProductBO and various repositories to perform business
 * logic related to products.
 */
@Component
public class ProductService {

	/**
	 * Business object (BO) used for product operations. Injected by Spring through
	 * the @Autowired annotation.
	 */
	@Autowired
	/* default */ProductBO productBO;

	/**
	 * Business object (BO) used for order details operations. Injected by Spring
	 * through the @Autowired annotation.
	 */
	@Autowired
	/* default */OrderDetailsBO bo2;

	/**
	 * Repository used for accessing product data. Initialized via constructor
	 * injection.
	 */
	private final ProductRepository productRepository;

	/**
	 * Repository used for accessing brand data. Initialized via constructor
	 * injection.
	 */
	private final BrandRepository brandRepository;

	/**
	 * Repository used for accessing category data. Initialized via constructor
	 * injection.
	 */
	private final CategoryRepository categoryRepo;

	/**
	 * Constructor for ProductService.
	 */
	@Autowired
	public ProductService(final ProductRepository productRepository, final BrandRepository brandRepository,
			final CategoryRepository categoryRepo) {
		this.productRepository = productRepository;
		this.brandRepository = brandRepository;
		this.categoryRepo = categoryRepo;

	}

	/**
	 * Inserts a new product into the system.
	 * 
	 * @param p The product to be inserted.
	 * @return The inserted product.
	 */
	public Product insert(final Product product) {
		return productBO.insert(product);
	}

	/**
	 * Finds a product by its ID.
	 * 
	 * @param id The ID of the product to be found.
	 * @return The product with the specified ID.
	 * @throws ResourceNotFoundException If the product is not found.
	 */
	public Product findProduct(final int productId) throws ResourceNotFoundException {
		return productBO.findProduct(productId);

	}

	/**
	 * Finds all products in the system.
	 * 
	 * @return A list of all products.
	 */
	public List<Product> findProducts() {
		return productBO.findProducts();

	}

	// Queries
	/**
	 * Finds products by their ID.
	 * 
	 * @param productId The ID of the product to be found.
	 * @return A list of products with the specified ID.
	 */
	public List<Product> findProductsById(final int productId) {
		return productBO.findProductsById(productId);
	}

	/**
	 * Finds products by their name.
	 * 
	 * @param productName The name of the product to be searched.
	 * @return A list of products with the specified name.
	 */
	public List<Product> findProductNames(final String productName) {
		return productBO.findProductNames(productName);
	}

	/**
	 * Finds products with a customized price filter.
	 * 
	 * @param price The price to filter products.
	 * @return A list of products matching the specified price filter.
	 */
	public List<ProductCustomized> findProductByPriceCustomized(final float price) {
		return productBO.findProductByPriceCustomized(price);

	}

	/**
	 * Finds all products ordered by name in descending order.
	 * 
	 * @return A list of all products ordered by name in descending order.
	 */
	public List<Product> findAllProductsByNameDescending() {
		return productBO.getAllProductsOrderedByNameDescending();
	}

	/**
	 * Finds products along with their order details.
	 * 
	 * @return A list of products with associated order details.
	 */
	public List<Product> findProductsAndOrderDetails() {
		return productBO.findProductsAndOrderDetails();
	}

	/**
	 * Finds products with outstanding orders.
	 * 
	 * @return A list of products that have orders left.
	 */
	public List<Product> findProductWithOrdersLeft() {
		return productBO.findProductWithOrdersLeft();
	}

	/**
	 * Finds products with customized order details based on price.
	 * 
	 * @param price The price to filter products.
	 * @return A list of products with customized order details.
	 */
	public List<ProductOrderCustomized> findByProductOrderCustomized(final float price) {
		return productBO.findByProductOrderCustomized(price);
	}

	/**
	 * Finds products and their category count.
	 * 
	 * @return A list of products and their associated category count.
	 */
	public List<ProductCategoryCustomized> findProductsCategoryCount() {
		return productBO.findProductsCategoryCount();
	}

	/**
	 * Finds product order details.
	 * 
	 * @return A list of product order details.
	 */
	public List<ProductOrderCBCustomized> findProductOrderDetails() {
		return productBO.findProductOrderDetails();

	}

	/**
	 * Finds the average price of products grouped by category.
	 * 
	 * @return A list of average prices by category.
	 */
	public List<CategoryPriceCustomized> findAvergePriceByCategory() {
		return productBO.findAvergePriceByCategory();
	}

	/**
	 * Finds product names in uppercase.
	 * 
	 * @return A list of product names in uppercase.
	 */
	public List<ProductUpper> findProductNamesUppercase() {
		return productBO.findProductNamesUppercase();

	}

	/**
	 * Finds the count of products by brand with a specified name and count.
	 * 
	 * @param name         The name of the brand.
	 * @param productCount The count of products to be filtered.
	 * @return A list of products and their count by brand.
	 */
	public List<ProductBrandCustomized> findProductCountByBrand(final String name, final int productCount) {
		return productBO.findProductCountByBrand(name, productCount);

	}

	public List<SalesReportCustomized> generateSalesReport() {
		return productRepository.generateSalesReport();
	}

	public List<CustomerOrderReportCustomized> generateCustomerOrderReport(int customerId) {
		return productRepository.generateCustomerOrderReport(customerId);
	}

	public List<Product> findProductsByBrandName(String brandName) {
		return productRepository.findProductsByBrandName(brandName);
	}

	public List<Product> findProductsByCategoryName(String categoryName) {
		return productRepository.findProductsByCategoryName(categoryName);
	}

	public boolean existsByProductName(String productName) {
		return productRepository.existsByProductName(productName);
	}

	public boolean existsByBarcode(String barcode) {
		return productRepository.existsByBarcode(barcode);
	}

	/**
	 * Maps a Product entity to its corresponding DTO.
	 * 
	 * @param product The Product entity to be mapped.
	 * @return The corresponding ProductDTO.
	 */
	public ProductDTO mapToDTO(final Product product) {
		final ProductDTO dto = new ProductDTO();
		dto.setProductId(product.getProductId());
		dto.setProductName(product.getProductName());
		dto.setStockAvailable(product.getStockAvailable());
		dto.setPrice(product.getPrice());
		dto.setBarcode(product.getBarCode());

		if (product.getBrand() != null) {
			dto.setBrandId(product.getBrand().getBrandId());
			dto.setBrandName(product.getBrand().getBrandName());
		}
		if (product.getCategory() != null) {
			dto.setCategoryId(product.getCategory().getCategoryId());
			dto.setCategoryName(product.getCategory().getCategoryName());
		}

		return dto;
	}

	/**
	 * Maps a ProductDTO to its corresponding entity.
	 * 
	 * @param productDTO The ProductDTO to be mapped.
	 * @return The corresponding Product entity.
	 * @throws ResourceNotFoundException If the brand or category referenced in the
	 *                                   DTO is not found.
	 */
	public Product mapToEntity(final ProductDTO productDTO) throws ResourceNotFoundException {
		final Product product = new Product();

		product.setProductName(productDTO.getProductName());
		product.setStockAvailable(productDTO.getStockAvailable());
		product.setPrice(productDTO.getPrice());
		product.setBarCode(productDTO.getBarcode());

		if (productDTO.getBrandId() != null) {
			product.setBrand(brandRepository.findById(productDTO.getBrandId()).orElseThrow(
					() -> new ResourceNotFoundException("Brand not found with id: " + productDTO.getBrandId())));
		}
		if (productDTO.getCategoryId() != null) {
			product.setCategory(categoryRepo.findById(productDTO.getCategoryId()).orElseThrow(
					() -> new ResourceNotFoundException("Category not found with id: " + productDTO.getCategoryId())));
		}

		return product;
	}

}
