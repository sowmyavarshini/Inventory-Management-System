package com.inventorymanagement.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventorymanagement.bo.OrderDetailsBO;
import com.inventorymanagement.bo.ProductBO;
import com.inventorymanagement.dao.CategoryPriceCustomized;
import com.inventorymanagement.dao.CityRepository;
import com.inventorymanagement.dao.CountryRepository;
import com.inventorymanagement.dao.CustomerOrderReportCustomized;
import com.inventorymanagement.dao.CustomerRepository;
import com.inventorymanagement.dao.ProductBrandCustomized;
import com.inventorymanagement.dao.ProductCategoryCustomized;
import com.inventorymanagement.dao.ProductCustomized;
import com.inventorymanagement.dao.ProductOrderCBCustomized;
import com.inventorymanagement.dao.ProductOrderCustomized;
import com.inventorymanagement.dao.SalesReportCustomized;
import com.inventorymanagement.dao.StateRepository;
import com.inventorymanagement.dto.BrandDTO;
import com.inventorymanagement.dto.CategoryDTO;
import com.inventorymanagement.dto.CategoryPriceCustomizedDTO;
import com.inventorymanagement.dto.CustomerDTO;
import com.inventorymanagement.dto.CustomerOrderReportCustomizedDTO;
import com.inventorymanagement.dto.OrderDetailsDTO;
import com.inventorymanagement.dto.ProductBrandCustomizedDTO;
import com.inventorymanagement.dto.ProductCategoryCustomizedDTO;
import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.dto.ProductOrderCBCustomizedDTO;
import com.inventorymanagement.dto.SalesReportDTO;
import com.inventorymanagement.entity.Brand;
import com.inventorymanagement.entity.Category;
import com.inventorymanagement.entity.City;
import com.inventorymanagement.entity.Country;
import com.inventorymanagement.entity.Customer;
import com.inventorymanagement.entity.OrderDetails;
import com.inventorymanagement.entity.Product;
import com.inventorymanagement.entity.State;
import com.inventorymanagement.exception.BadRequestException;
import com.inventorymanagement.exception.CustomException;
import com.inventorymanagement.exception.DuplicateEntryException;
import com.inventorymanagement.exception.PasswordMismatchException;
import com.inventorymanagement.exception.ResourceNotFoundException;
import com.inventorymanagement.exception.UserNotFoundException;
import com.inventorymanagement.service.BrandService;
import com.inventorymanagement.service.CategoryService;
import com.inventorymanagement.service.CustomerService;
import com.inventorymanagement.service.OrderDetailsService;
import com.inventorymanagement.service.ProductService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

/**
 * The InventoryManagement controller handles REST API requests related to
 * inventory operations.
 */

@CrossOrigin("*")
@RestController
@Validated
public class InventoryManagementService {

	/**
	 * Logger for logging messages related to product operations.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
	/**
	 * Pattern to match alphabetic strings.
	 */
	private static final Pattern ALPHABET_PATTERN = Pattern.compile("^[a-zA-Z]+$");

	/**
	 * Service for managing products.
	 */
	private final ProductService productService;
	/**
	 * Service for managing brands.
	 */
	private final BrandService brandService;
	/**
	 * Service for managing categories.
	 */
	private final CategoryService categoryService;
	/**
	 * Service for managing order details.
	 */
	private final OrderDetailsService orderService;
	/**
	 * Business object for product operations.
	 */
	private final ProductBO productBO;
	/**
	 * Business object for order details operations.
	 */
	private final OrderDetailsBO orderDetailsBO;

	private final CustomerService customerService;

	private final CityRepository cityRepository;

	private final StateRepository stateRepository;

	private final CountryRepository countryRepository;

	private final CustomerRepository customerRepository;

	/**
	 * Constructor for InventoryManagement.
	 * 
	 */
	@Autowired
	public InventoryManagementService(final ProductService productService, final BrandService brandService,
			final CategoryService categoryService, final OrderDetailsService orderService, final ProductBO productBO,
			final OrderDetailsBO orderDetailsBO, final CustomerService customerService,
			final CityRepository cityRepository, final StateRepository stateRepository,
			final CountryRepository countryRepository, final CustomerRepository customerRepository) {
		this.productService = productService;
		this.brandService = brandService;
		this.categoryService = categoryService;
		this.orderService = orderService;
		this.productBO = productBO;
		this.orderDetailsBO = orderDetailsBO;
		this.customerService = customerService;
		this.cityRepository = cityRepository;
		this.stateRepository = stateRepository;
		this.countryRepository = countryRepository;
		this.customerRepository = customerRepository;
	}

//	Product APIs
	/**
	 * Creates a product
	 * 
	 * @return The product created
	 * 
	 */
	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public ProductDTO addProduct(@Valid @RequestBody final ProductDTO msg) throws ResourceNotFoundException {
		try {
			if (LOG.isInfoEnabled()) {
				LOG.info("Adding Product API called...");
			}
			Brand brandResponse;
			try {
				brandResponse = brandService.findBrand(msg.getBrandId());
			} catch (NoSuchElementException ex) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Brand Id Not Found for ID: {}", msg.getBrandId());
				}
				throw new ResourceNotFoundException("Brand Id Not Found.");
			}
			Category categoryResponse;
			try {
				categoryResponse = categoryService.findCategory(msg.getCategoryId());
			} catch (NoSuchElementException ex) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Category Id Not Found for ID: {}", msg.getCategoryId());
				}
				throw new ResourceNotFoundException("Category Id Not Found.");
			}

			final Product product = productService.mapToEntity(msg);

			if (productService.existsByProductName(product.getProductName())) {
				throw new DuplicateEntryException("Product name already exists.");
			}

			if (productService.existsByBarcode(product.getBarCode())) {
				throw new DuplicateEntryException("Barcode already exists.");
			}

			final Product response = productService.insert(product);

			if (LOG.isInfoEnabled()) {
				LOG.info("Product successfully created with ID: {}", response.getProductId());
			}
			return productService.mapToDTO(response);

		} catch (DuplicateEntryException | ResourceNotFoundException ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Error: {}", ex.getMessage());
			}
			throw ex; // Propagate the exception to be handled by the global exception handler

		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error: {}", ex.getMessage());
			}
			throw new CustomException("Unexpected error occurred: " + ex.getMessage());
		}
	}

	/**
	 * Retrieves product by id
	 * 
	 * @return The product with the entered id
	 * @throws ResourceNotFoundException If no product are found.
	 */
	@RequestMapping(value = "/fetchProductById", method = RequestMethod.GET)
	public ProductDTO fetchProduct(@RequestParam final int productId)
			throws ResourceNotFoundException, BadRequestException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching product by ID: {}", productId);
		}
		if (productId <= 0) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Invalid product ID provided: {}", productId);
			}
			throw new BadRequestException("Invalid product ID provided");
		}
		try {
			final Product product = productService.findProduct(productId);
			if (product == null) {
				LOG.error("Product with ID {} not found", productId);
				throw new ResourceNotFoundException("Product not found with ID: " + productId);
			}

			final ProductDTO dto = productService.mapToDTO(product);

			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched product with ID: {}", productId);
			}
			return dto;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while fetching product with ID: {}", productId, ex);
			}
			throw ex;
		}

	}

	/**
	 * Retrieves all the products
	 * 
	 * @return A list of Products from the database
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/fetchProducts", method = RequestMethod.GET)
	public List<ProductDTO> fetchAllProducts() throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetch all products....");
		}
		try {
			final List<Product> list = productService.findProducts();

			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No products found.");
				}
				throw new ResourceNotFoundException("No Products found.");
			}

			final List<ProductDTO> listDTO = list.stream().map(productService::mapToDTO).collect(Collectors.toList());
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched all products.");
			}
			return listDTO;
		} catch (ResourceNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Resource not found: {}", e.getMessage());
			}
			throw e;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("An unexpected error occurred while fetching products", e);
			}
			throw e;
		}

	}

	/**
	 * Updates the product.
	 * 
	 * @return ProductDTO containing the updated product data.
	 * 
	 */
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public ProductDTO updateProduct(@Valid @RequestBody final ProductDTO msg) throws ResourceNotFoundException {
		try {
			if (LOG.isInfoEnabled()) {
				LOG.info("Updating Product API called...");
			}
			final Product existingProduct;
			try {
				existingProduct = productService.findProduct(msg.getProductId());
			} catch (ResourceNotFoundException ex) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Product ID not found: {}", msg.getProductId());
				}
				throw new ResourceNotFoundException("Product ID not found: " + msg.getProductId());
			}
			final Brand brandResponse;
			try {
				brandResponse = brandService.findBrand(msg.getBrandId());
			} catch (NoSuchElementException ex) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Brand Id Not Found for ID: {}", msg.getBrandId());
				}
				throw new ResourceNotFoundException("Brand Id Not Found.");
			}
			final Category categoryResponse;
			try {
				categoryResponse = categoryService.findCategory(msg.getCategoryId());
			} catch (NoSuchElementException ex) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Category Id Not Found for ID: {}", msg.getCategoryId());
				}
				throw new ResourceNotFoundException("Category Id Not Found.");
			}

			existingProduct.setProductName(msg.getProductName());
			existingProduct.setStockAvailable(msg.getStockAvailable());
			existingProduct.setPrice(msg.getPrice());
			existingProduct.setBarCode(msg.getBarcode());
			existingProduct.setBrand(brandResponse);
			existingProduct.setCategory(categoryResponse);
			final Product updatedProduct = productService.insert(existingProduct);

			// Set the brand and category IDs in the response DTO
			msg.setBrandId(brandResponse.getBrandId());
			msg.setCategoryId(categoryResponse.getCategoryId());
			productBO.adjustStock(existingProduct, existingProduct.getStockAvailable(), msg.getStockAvailable());
			if (LOG.isInfoEnabled()) {
				LOG.info("Product successfully updated with ID: {}", msg.getProductId());
			}
			return productService.mapToDTO(updatedProduct);

		} catch (ResourceNotFoundException ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Error: {}", ex.getMessage());
			}
			throw ex; // Propagate the exception to be handled by the global exception handler

		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error: {}", ex.getMessage());
			}
			throw new CustomException("Unexpected error occurred: " + ex.getMessage());
		}
	}

	/**
	 * Retrieves product order details by joining columns from product,
	 * orderDetails, brand, and category tables.
	 * 
	 * @return A list of BrandDTO containing the brands
	 * @throws ResourceNotFoundException If no brands are found.
	 */
	@RequestMapping(value = "/fetchBrands", method = RequestMethod.GET)
	public List<BrandDTO> fetchAllBrands() throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Received request to fetch all brands...");
		}
		try {
			final List<Brand> list = brandService.findBrands();
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No brands found.");
				}
				throw new ResourceNotFoundException("No brands found.");
			}
			final List<BrandDTO> listDTO = new ArrayList<>();
			for (final Brand p : list) {
				final BrandDTO dto = new BrandDTO();
				dto.setBrandId(p.getBrandId());
				dto.setBrandName(p.getBrandName());
				listDTO.add(dto);
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched brand details..");
			}
			return listDTO;

		} catch (ResourceNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Resource not found: {}", e.getMessage());
			}
			throw e;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("An unexpected error occurred while fetching brands.", e);
			}
			throw e;
		}

	}

	/**
	 * Retrieves all category from database
	 * 
	 * @return A list of CategoryDTO containing the combined data.
	 * @throws ResourceNotFoundException If no products are found.
	 */
//  Fetch all - Categories
	@RequestMapping(value = "/fetchCategories", method = RequestMethod.GET)
	public List<CategoryDTO> fetchAllCategory() throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Received request to fetch all categories...");
		}
		try {
			final List<Category> list = categoryService.findCategories();
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No categories found.");
				}
				throw new ResourceNotFoundException("No cetgories found.");
			}
			final List<CategoryDTO> listDTO = new ArrayList<>();
			for (final Category p : list) {
				final CategoryDTO dto = new CategoryDTO();
				dto.setCategoryId(p.getCategoryId());
				dto.setCategoryName(p.getCategoryName());
				listDTO.add(dto);
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched categories..");
			}
			return listDTO;

		} catch (ResourceNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Resource not found: {}", e.getMessage());
			}
			throw e;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("An unexpected error occurred while fetching categories.", e);
			}
			throw e;
		}

	}

//  Order Details API
	/**
	 * Creates order details
	 * 
	 * @return The order detail created
	 * 
	 */
	@RequestMapping(value = "/createOrderDetails", method = RequestMethod.POST)
	public OrderDetailsDTO createOrderDetails(@Valid @RequestBody final OrderDetailsDTO msg)
			throws ResourceNotFoundException {
		try {
			if (LOG.isInfoEnabled()) {
				LOG.info("Adding Order details API called...");
			}

//			try {
//
//				final OrderDetails existingOrder = orderService.findOrderDetail(msg.getOrderId());
//
//				if (existingOrder != null) {
//					throw new DuplicateEntryException("OrderDetails ID already exists: " + msg.getProductId());
//				}
//			} catch (ResourceNotFoundException ex) {
//
//				if (LOG.isInfoEnabled()) {
//					LOG.info("Order ID not found, proceeding to create new product.");
//				}
//			}

			final Product productResponse = productService.findProduct(msg.getProductId());
			if (productResponse == null) {
				throw new ResourceNotFoundException("Product not found with ID: " + msg.getProductId());
			}
			productBO.validateProductAvailability(productResponse, msg.getOrderedQuantity());
			productBO.reduceQuantity(productResponse, msg.getOrderedQuantity());

			final OrderDetails order = orderService.mapToEntity(msg);
			final OrderDetails savedOrderDetails = orderService.insert(order);
			if (LOG.isInfoEnabled()) {
				LOG.info("Order Details successfully created with ID: {}", msg.getOrderId());
			}

			return orderService.mapToDTO(savedOrderDetails);
		} catch (DuplicateEntryException | ResourceNotFoundException ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Error: {}", ex.getMessage());
			}
			throw ex;

		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error: {}", ex.getMessage());
			}
			throw new CustomException("Unexpected error occurred: " + ex.getMessage());
		}
	}

	/**
	 * Retrieves order details based on requested orderId
	 * 
	 * @param orderId requests the id of the order detail
	 * @return ProductOrderDetailsDTO containing the order details
	 * @throws ResourceNotFoundException If no order detail is found.
	 */
	@RequestMapping(value = "/fetchOrderById", method = RequestMethod.GET)
	public OrderDetailsDTO fetchOrder(@RequestParam final int orderId)
			throws ResourceNotFoundException, BadRequestException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching order detail by ID: {}", orderId);
		}
		if (orderId <= 0) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Invalid Order detail ID provided: {}", orderId);
			}
			throw new BadRequestException("Invalid Order Detail ID provided");
		}
		try {
			final OrderDetails order = orderService.findOrderDetail(orderId);
			if (order == null) {
				LOG.error("OrderDetail with ID {} not found", orderId);
				throw new ResourceNotFoundException("OrderDetail not found with ID: " + orderId);
			}

			final OrderDetailsDTO dto = orderService.mapToDTO(order);
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched Order detail with ID: {}", orderId);
			}
			return dto;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while fetching product with ID: {}", orderId, ex);
			}
			throw ex;
		}
	}

	/**
	 * Retrieves order details from orderDetails table.
	 * 
	 * @return A list of OrderDetailsDTO the combined data.
	 * @throws ResourceNotFoundException If no orders are found.
	 */
	@RequestMapping(value = "/fetchOrderDetails", method = RequestMethod.GET)
	public List<OrderDetailsDTO> fetchAllOrders() throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Received request to fetch all order details...");
		}
		try {
			final List<OrderDetails> list = orderService.findOrderDetails();
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No order details found.");
				}
				throw new ResourceNotFoundException("No order details found.");
			}

			final List<OrderDetailsDTO> listDTO = list.stream().map(orderService::mapToDTO)
					.collect(Collectors.toList());

			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched order details..");
			}
			return listDTO;
		} catch (ResourceNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Resource not found: {}", e.getMessage());
			}
			throw e;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("An unexpected error occurred while fetching order details", e);
			}
			throw e;
		}

	}

	/**
	 * Updates the order details.
	 * 
	 * @return OrderDetailsDTO that contains the updated data
	 * 
	 */
	@RequestMapping(value = "/updateOrderDetails", method = RequestMethod.POST)
	public OrderDetailsDTO updateOrderDetails(@Valid @RequestBody final OrderDetailsDTO msg)
			throws ResourceNotFoundException {
		try {
			if (LOG.isInfoEnabled()) {
				LOG.info("Updating Order details API called...");
			}
			final OrderDetails existingOrder;
			// Fetch the existing OrderDetails from the database
			try {
				existingOrder = orderService.findOrderDetail(msg.getOrderId());
			} catch (ResourceNotFoundException e) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Orderdetails id is not found : {}", msg.getOrderId());
				}
				throw new ResourceNotFoundException("Order Id not found.");

			}

			// Preserve the orderedDate and deliveryDate
			final Date orderedDate = existingOrder.getOrderedDate();
			final Date deliveryDate = existingOrder.getDeliveryDate();

			// Fetch the Product based on the provided productId
			final Product productResponse;
			try {
				productResponse = productService.findProduct(msg.getProductId());
			} catch (NoSuchElementException e) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Product Id not found:{}", msg.getProductId());
				}
				throw new ResourceNotFoundException("Product Id not found.");
			}
			productBO.adjustStock(productResponse, existingOrder.getOrderedQuantity(), msg.getOrderedQuantity());
			productBO.updateOrderDetails(existingOrder, msg, productResponse);

			// Update the necessary fields
			existingOrder.setOrderId(msg.getOrderId());
			existingOrder.setOrderedQuantity(msg.getOrderedQuantity());
			existingOrder.setProduct(productResponse);

			// Set the preserved dates back to the OrderDetails object
			existingOrder.setOrderedDate(orderedDate);
			existingOrder.setDeliveryDate(deliveryDate);

			// Save the updated OrderDetails to the database
			orderService.insert(existingOrder);

			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully updated orderdetails...");
			}
			return orderService.mapToDTO(existingOrder);
		} catch (ResourceNotFoundException ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error: {}", ex.getMessage());
			}
			throw ex;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error: {}", ex.getMessage());
			}
			throw new CustomException("Unexpected error occured:" + ex.getMessage());

		}
	}

//	Queries
	/**
	 * Retrieves products that is greater than the productId
	 * 
	 * @param productId of the product
	 * @return A list of ProductDTO containing the combined data.
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/fetchProductsById", method = RequestMethod.GET)
	public List<ProductDTO> fetchProductsById(@RequestParam final int productId) throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Received request to fetch products with id greater than: {}", productId);
		}
		try {

			final List<Product> list = productService.findProductsById(productId);
			if (list == null || list.isEmpty()) {
				throw new ResourceNotFoundException(
						"No list with products found with ID greater than {}: " + productId);
			}

			final List<ProductDTO> listDTO = list.stream().map(productService::mapToDTO).collect(Collectors.toList());
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched products with id greater than {}", productId);
			}
			return listDTO;
		} catch (ResourceNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Resource not found: {}", e.getMessage());
			}
			throw e;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("An unexpected error occurred while fetching products", e);
			}
			throw e;
		}
	}

	/**
	 * Retrieves products greater than price
	 * 
	 * @Param float price, that is compared with product price
	 * @return A list of ProductCustomized containing the combined data.
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/findProductByPrice", method = RequestMethod.GET)
	public List<ProductCustomized> findProductByPrice(@RequestParam final float price)
			throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Received request to fetch products with price greater than: {}", price);
		}
		try {
			final List<ProductCustomized> list = productService.findProductByPriceCustomized(price);
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No products found with price greater than {}", price);
				}
				throw new ResourceNotFoundException("No Products found.");
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched products with price greater than {}", price);
			}
			return list;
		} catch (ResourceNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Resource not found: {}", e.getMessage());
			}
			throw e;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("An unexpected error occurred while fetching products", e);
			}
			throw e;
		}
	}

	/**
	 * Retrieves product that matches the name
	 * 
	 * @param String name requests the name of the product
	 * @return A list of ProductDTO containing the combined data.
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/findProductByNames", method = RequestMethod.GET)
	public List<ProductDTO> findProductByNames(@RequestParam final String name) throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Finding product by name: {}", name);
		}
		// Validate the product name
		if (!ALPHABET_PATTERN.matcher(name).matches()) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Invalid product name provided: {}", name);
			}
			throw new IllegalArgumentException("Product name must contain only alphabets.");
		}
		try {
			final List<Product> list = productService.findProductNames(name);
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No products found by name{}", name);
				}
				throw new ResourceNotFoundException("No Products found.");
			}

			final List<ProductDTO> listDTO = list.stream().map(productService::mapToDTO).collect(Collectors.toList());
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully found product with name: {}", name);
			}
			return listDTO;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while finding product with name: {}", name, ex);
			}
			throw ex;
		}
	}

	/**
	 * Retrieves products in the descending order of their names.
	 * 
	 * @return A list of ProductDTO containing the data.
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/findAllProductsByNameDescending", method = RequestMethod.GET)
	public List<ProductDTO> findAllProductsByNameDescending() throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Finding product by name in descending order.");
		}
		try {
			final List<Product> list = productService.findAllProductsByNameDescending();
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No products found.");
				}
				throw new ResourceNotFoundException("No Products found.");
			}

			final List<ProductDTO> listDTO = list.stream().map(productService::mapToDTO).collect(Collectors.toList());
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched products by name in descending.");
			}
			return listDTO;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while fetching product by name in descending order.", ex);
			}

			throw ex;
		}
	}

	/**
	 * Retrieves product order details greater than price by joining columns from
	 * product, orderDetails table
	 * 
	 * @return A list of ProductOrderCustomizedDTO containing the combined data.
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/findByProductOrderCustomized", method = RequestMethod.GET)
	public List<ProductOrderCustomized> findByProductOrderCustomized(@RequestParam final float price)
			throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching products where price is greater than {}", price);
		}
		try {
			final List<ProductOrderCustomized> list = productService.findByProductOrderCustomized(price);
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No products found for price greater than {}.", price);
				}
				throw new ResourceNotFoundException("No Products found.");
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched products with price greater than {}", price);
			}

			return list;

		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while fetching product by name in descending order.", ex);
			}

			throw ex;
		}
	}

	/**
	 * Retrieves count of products based on category
	 * 
	 * @return A list of category and their category count
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/findByProductCategoryCount", method = RequestMethod.GET)
	public List<ProductCategoryCustomizedDTO> findProductsCategoryCount() throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching product count by category.");
		}
		try {

			final List<ProductCategoryCustomized> list = productService.findProductsCategoryCount();
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("Product count by category not found.");
				}
				throw new ResourceNotFoundException("No Products found.");
			}
			final List<ProductCategoryCustomizedDTO> listDTO = new ArrayList<>();
			for (final ProductCategoryCustomized p : list) {
				final ProductCategoryCustomizedDTO dto = new ProductCategoryCustomizedDTO();
				dto.setCategoryName(p.getCategoryName());
				dto.setProductCount(p.getProductCount());
				listDTO.add(dto);
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched product count grouped by category");
			}
			return listDTO;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while fetching product by category.", ex);
			}

			throw ex;
		}
	}

	/**
	 * Retrieves the average price of products grouped by category.
	 * 
	 * @return A list of CategoryPriceCustomizedDTO containing the category name and
	 *         average price.
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/findAvergePriceByCategory", method = RequestMethod.GET)
	public List<CategoryPriceCustomizedDTO> findAvergePriceByCategory() throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching average price of product grouped by category.");
		}
		try {

			final List<CategoryPriceCustomized> list = productService.findAvergePriceByCategory();
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("Product average price by category not found.");
				}
				throw new ResourceNotFoundException("No Products found.");
			}
			final List<CategoryPriceCustomizedDTO> listDTO = new ArrayList<>();
			for (final CategoryPriceCustomized p : list) {
				final CategoryPriceCustomizedDTO dto = new CategoryPriceCustomizedDTO();
				dto.setCategoryName(p.getCategoryName());
				dto.setAveragePrice(p.getAveragePrice());
				listDTO.add(dto);
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched product average price grouped by category");
			}
			return listDTO;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while fetching product average price by category.", ex);
			}

			throw ex;
		}
	}

	/**
	 * Retrieves product count based on brand
	 * 
	 * @Param String name and int number requests the name of the brand and the
	 *        number of products in the brand
	 * @return A list of ProductBrandCustomizedDTO containing the combined data.
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/findProductCountByBrand", method = RequestMethod.GET)
	public List<ProductBrandCustomizedDTO> findProductCountByBrand(@RequestParam final String name,
			@RequestParam final int number) throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching count of product grouped by brand name {} that is greater than {} in ascending order.",
					name, number);
		}
		// Validate the product name
		if (!ALPHABET_PATTERN.matcher(name).matches()) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Invalid product name provided: {}", name);
			}
			throw new IllegalArgumentException("Product name must contain only alphabets.");
		}
		try {

			final List<ProductBrandCustomized> list = productService.findProductCountByBrand(name, number);
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("Product count grouped by brand not found.");
				}
				throw new ResourceNotFoundException("No Products found.");
			}
			final List<ProductBrandCustomizedDTO> listDTO = new ArrayList<>();
			for (final ProductBrandCustomized p : list) {
				final ProductBrandCustomizedDTO dto = new ProductBrandCustomizedDTO();
				dto.setBrandName(p.getBrandName());
				dto.setProductCount(p.getProductCount());
				listDTO.add(dto);
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched product count grouped by brand");
			}
			return listDTO;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while fetching product count by brand", ex);
			}

			throw ex;
		}
	}

	/**
	 * Retrieves product order details by joining columns from product,
	 * orderDetails, brand, and category tables.
	 * 
	 * @return A list of ProductOrderCBCustomizedDTO containing the combined data.
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/findProductOrderDetails", method = RequestMethod.GET)
	public List<ProductOrderCBCustomizedDTO> findProductOrderDetails() throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching columns from product, orderDetails, brand and category tables.");
		}
		try {

			final List<ProductOrderCBCustomized> list = productService.findProductOrderDetails();
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No list found");
				}
				throw new ResourceNotFoundException("No Products found.");
			}
			final List<ProductOrderCBCustomizedDTO> listDTO = new ArrayList<>();
			for (final ProductOrderCBCustomized p : list) {
				final ProductOrderCBCustomizedDTO dto = new ProductOrderCBCustomizedDTO();
				dto.setProductId(p.getProductId());
				dto.setProductName(p.getProductName());
				dto.setOrderId(p.getOrderId());
				dto.setOrderedQuantity(p.getOrderedQuantity());
				dto.setBrandName(p.getBrandName());
				dto.setCategoryName(p.getCategoryName());
				listDTO.add(dto);
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully joined tables product, orderDetails, brand and category.");
			}
			return listDTO;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while performing join.", ex);
			}

			throw ex;
		}

	}

	/**
	 * Retrieves order details for a product.
	 * 
	 * @return A list of OrderDetailsDTO containing the combined data.
	 * @throws ResourceNotFoundException If no products are found.
	 */
	@RequestMapping(value = "/findOrdersForProduct", method = RequestMethod.GET)
	public List<OrderDetailsDTO> fetchOrdersForProduct(@RequestParam final int productId)
			throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching Orders for a product...");
		}
		try {
			final List<OrderDetails> list = orderService.findProductOrderDetails(productId);
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No list found");
				}
				throw new ResourceNotFoundException("No orders found. Please order.");
			}
			final List<OrderDetailsDTO> listDTO = list.stream().map(orderService::mapToDTO)
					.collect(Collectors.toList());
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched order details for a product.");
			}
			return listDTO;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while fetching orders for a product.", ex);
			}

			throw ex;
		}
	}

	@RequestMapping(value = "/generateSalesReport", method = RequestMethod.GET)
	public List<SalesReportDTO> generateSalesReport() throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Generating Sales Report...");
		}
		try {

			final List<SalesReportCustomized> list = productService.generateSalesReport();
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No list found");
				}
				throw new ResourceNotFoundException("No orders found.");
			}
			final List<SalesReportDTO> listDTO = new ArrayList<>();
			for (final SalesReportCustomized p : list) {
				final SalesReportDTO dto = new SalesReportDTO();
				dto.setProductId(p.getProductId());
				dto.setProductName(p.getProductName());
				dto.setBrand(p.getBrand());
				dto.setCategory(p.getCategory());
				dto.setStockAvailable(p.getStockAvailable());
				dto.setPrice(p.getPrice());
				dto.setCustomerId(p.getCustomerId());
				dto.setOrderId(p.getOrderId());
				dto.setOrderedQuantity(p.getOrderedQuantity());
				dto.setAmountPaid(p.getAmountPaid());
				listDTO.add(dto);
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully generated sales report.");
			}
			return listDTO;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occured while generating report", ex);
			}

			throw ex;
		}

	}

	@RequestMapping(value = "/ generateCustomerOrderReport", method = RequestMethod.GET)
	public List<CustomerOrderReportCustomizedDTO> generateCustomerOrderReport(@RequestParam final int customerId)
			throws ResourceNotFoundException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Generating report for a customer");
		}
		try {

			// Check if the customer exists
			if (!customerService.customerExists(customerId)) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Customer not found with ID: " + customerId);
				}
				throw new ResourceNotFoundException("Customer not found with ID: " + customerId);
			}

			final List<CustomerOrderReportCustomized> list = productService.generateCustomerOrderReport(customerId);
			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No list found");
				}
				throw new ResourceNotFoundException("No orders found.");
			}
			final List<CustomerOrderReportCustomizedDTO> listDTO = new ArrayList<>();
			for (final CustomerOrderReportCustomized p : list) {
				final CustomerOrderReportCustomizedDTO dto = new CustomerOrderReportCustomizedDTO();
				dto.setCustomerId(p.getCustomerId());
				dto.setUserName(p.getUserName());
				dto.setProductId(p.getProductId());
				dto.setProductName(p.getProductName());
				dto.setPrice(p.getPrice());
				dto.setOrderedQuantity(p.getOrderedQuantity());
				dto.setAmountPaid(p.getAmountPaid());
				listDTO.add(dto);
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully generated customer report.");
			}
			return listDTO;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error occurred while generating report", ex);
			}

			throw ex;
		}

	}

	@RequestMapping(value = "/findProductsByBrandName", method = RequestMethod.GET)
	public List<ProductDTO> findProductsByBrandName(@RequestParam final String brandName)
			throws ResourceNotFoundException, BadRequestException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching product by brand: {}", brandName);
		}

		try {
			if (!ALPHABET_PATTERN.matcher(brandName).matches()) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Invalid brand name provided: {}", brandName);
				}
				throw new IllegalArgumentException("Brand name must contain only alphabets.");
			}

			final List<Product> list = productService.findProductsByBrandName(brandName);

			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No products found.");
				}
				throw new ResourceNotFoundException("No Products found.");
			}

			final List<ProductDTO> listDTO = list.stream().map(productService::mapToDTO).collect(Collectors.toList());
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched all products.");
			}
			return listDTO;
		} catch (ResourceNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Resource not found: {}", e.getMessage());
			}
			throw e;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("An unexpected error occurred while fetching products", e);
			}
			throw e;
		}

	}

	@RequestMapping(value = "/findProductsByCategoryName", method = RequestMethod.GET)
	public List<ProductDTO> findProductsByCategoryName(@RequestParam final String categoryName)
			throws ResourceNotFoundException, BadRequestException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Fetching product by category {}", categoryName);
		}
		try {
			if (!ALPHABET_PATTERN.matcher(categoryName).matches()) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Invalid category name provided: {}", categoryName);
				}
				throw new IllegalArgumentException("Category name must contain only alphabets.");
			}
			final List<Product> list = productService.findProductsByCategoryName(categoryName);

			if (list.isEmpty()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("No products found.");
				}
				throw new ResourceNotFoundException("No Products found.");
			}

			final List<ProductDTO> listDTO = list.stream().map(productService::mapToDTO).collect(Collectors.toList());
			if (LOG.isInfoEnabled()) {
				LOG.info("Successfully fetched all products.");
			}
			return listDTO;
		} catch (ResourceNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Resource not found: {}", e.getMessage());
			}
			throw e;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("An unexpected error occurred while fetching products", e);
			}
			throw e;
		}

	}

	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public CustomerDTO createCustomer(@Valid @RequestBody final CustomerDTO customerDTO)
			throws ResourceNotFoundException {
		// Find related entities by names
		try {
			if (LOG.isInfoEnabled()) {
				LOG.info("Registering new customer...");
			}

			if (customerRepository.existsByUsername(customerDTO.getUserName())) {
				throw new DataIntegrityViolationException("Username is already taken.");
			}
			if (customerRepository.existsByEmail(customerDTO.getEmail())) {
				throw new DataIntegrityViolationException("Email is already in use.");
			}

			City city = cityRepository.findByCityName(customerDTO.getCityName())
					.orElseThrow(() -> new RuntimeException("City not found"));
			State state = stateRepository.findByStateName(customerDTO.getStateName())
					.orElseThrow(() -> new RuntimeException("State not found"));
			Country country = countryRepository.findByCountryName(customerDTO.getCountryName())
					.orElseThrow(() -> new RuntimeException("Country not found"));

			// Create Customer entity
			Customer customer = new Customer();
			customer.setUsername(customerDTO.getUserName());
			customer.setUserpassword(customerDTO.getUserPassword());
			customer.setEmail(customerDTO.getEmail());
			customer.setCity(city);
			customer.setState(state);
			customer.setCountry(country);

			// Save customer
			Customer cust = customerService.insert(customer);
			customer.setCustomerId(cust.getCustomerId());

			if (LOG.isInfoEnabled()) {
				LOG.info("Customer registered successfully ID: {}", cust.getCustomerId());
			}
			return customerDTO;
		} catch (DataIntegrityViolationException ex) {

			if (LOG.isErrorEnabled()) {
				LOG.error("Data integrity violation: {}", ex.getMessage());
			}
			throw new CustomException("Data integrity error: " + ex.getMessage());

		} catch (Exception ex) {

			if (ex instanceof ConstraintViolationException) {
				throw ex;
			}

			if (LOG.isErrorEnabled()) {
				LOG.error("Unexpected error: {}", ex.getMessage());
			}
			throw new CustomException("Unexpected error occurred: " + ex.getMessage());
		}
	}

	@RequestMapping(value = "/checkProductName", method = RequestMethod.GET)
	public boolean checkProductName(@RequestParam final String productName) {
		boolean isUnique = productService.existsByProductName(productName);
		return isUnique;
	}

	@RequestMapping(value = "/checkBarCode", method = RequestMethod.GET)
	public boolean checkBarCode(@RequestParam final String barcode) {
		boolean isUnique = productService.existsByBarcode(barcode);
		return isUnique;
	}

	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
	public boolean checkUserName(@RequestParam final String username) {
		boolean isUnique = customerService.userExists(username);
		return isUnique;
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	public boolean checkEmail(@RequestParam final String email) {
		boolean isUnique = customerService.emailExists(email);
		return isUnique;
	}

	@RequestMapping(value = "/checkCityName", method = RequestMethod.GET)
	public boolean checkCityName(@RequestParam final String cityName) {
		boolean exists = cityRepository.existsByCityName(cityName);
		return exists;
	}

	@RequestMapping(value = "/checkStateName", method = RequestMethod.GET)
	public boolean checkStateName(@RequestParam final String stateName) {
		boolean isUnique = stateRepository.existsByStateName(stateName);
		return isUnique;
	}

	@RequestMapping(value = "/checkCountryName", method = RequestMethod.GET)
	public boolean checkCountryName(@RequestParam final String countryName) {
		boolean isUnique = countryRepository.existsByCountryName(countryName);
		return isUnique;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public boolean login(@RequestParam String username, @RequestParam String userpassword) {
		LOG.info("Attempting to authenticate user with username: {}", username);

		Customer customer = customerRepository.findByUsername(username);
		if (customer == null) {
			LOG.error("User not found with username: {}", username);
			throw new UserNotFoundException(username);
		}

		boolean isPasswordMatch = userpassword.matches(customer.getUserpassword());
		if (isPasswordMatch) {
			LOG.info("Authentication successful for user: {}", username);
			return true;
		} else {
			LOG.warn("Password mismatch for user: {}", username);
			throw new PasswordMismatchException(username);
		}
	}

}
