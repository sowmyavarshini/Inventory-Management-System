package com.inventorymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventorymanagement.bo.OrderDetailsBO;
import com.inventorymanagement.dao.ProductRepository;
import com.inventorymanagement.dto.OrderDetailsDTO;
import com.inventorymanagement.entity.OrderDetails;
import com.inventorymanagement.entity.Product;
import com.inventorymanagement.exception.ResourceNotFoundException;

/**
 * Service class responsible for handling order details-related operations. It
 * interacts with the OrderDetailsBO to perform business logic related to order
 * details and products.
 */
@Component
public class OrderDetailsService {
	/**
	 * Business object (BO) used for order details operations. Injected by Spring
	 * through the @Autowired annotation.
	 */
	@Autowired
	/* default */OrderDetailsBO orderBO = null;

	/**
	 * Repository used for accessing product data. Injected by Spring through
	 * the @Autowired annotation.
	 */
	@Autowired
	/* default */ProductRepository productRepository;

	/**
	 * Constructor for OrderDetailsService.
	 * 
	 * @param orderBO           The OrderDetailsBO instance to be used by this
	 *                          service.
	 * @param productRepository The ProductRepository instance for accessing product
	 *                          data.
	 */
	public OrderDetailsService(final OrderDetailsBO orderBO, final ProductRepository productRepository) {
		this.orderBO = orderBO;
		this.productRepository = productRepository;
	}

	/**
	 * Inserts a new order detail into the system.
	 * 
	 * @param order The order detail to be inserted.
	 * @return The inserted order detail.
	 */
	public OrderDetails insert(final OrderDetails order) {
		return orderBO.insert(order);
	}

	/**
	 * Finds an order detail by its ID.
	 * 
	 * @param orderId The ID of the order detail to be found.
	 * @return The order detail with the specified ID.
	 * @throws ResourceNotFoundException If the order detail is not found.
	 */
	public OrderDetails findOrderDetail(final int orderId) throws ResourceNotFoundException {
		return orderBO.findOrderDetail(orderId);

	}

	/**
	 * Finds all order details in the system.
	 * 
	 * @return A list of all order details.
	 */
	public List<OrderDetails> findOrderDetails() {
		return orderBO.findOrderDetails();

	}

	/**
	 * Finds all order details for a specific product.
	 * 
	 * @param productId The ID of the product whose order details are to be found.
	 * @return A list of order details associated with the specified product.
	 * @throws ResourceNotFoundException If the product is not found.
	 */
	public List<OrderDetails> findProductOrderDetails(final int productId) throws ResourceNotFoundException {
		final Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
		final List<OrderDetails> orderDetailsList = product.getOrder();
		return orderDetailsList;

	}

	/**
	 * Maps an OrderDetails entity to its corresponding DTO.
	 * 
	 * @param orderDetails The OrderDetails entity to be mapped.
	 * @return The corresponding OrderDetailsDTO.
	 */
	public OrderDetailsDTO mapToDTO(final OrderDetails orderDetails) {
		if (orderDetails == null) {
			return null;
		}
		final OrderDetailsDTO dto = new OrderDetailsDTO();
		dto.setOrderId(orderDetails.getOrderId());
		dto.setOrderedQuantity(orderDetails.getOrderedQuantity());
		dto.setOrderedDate(orderDetails.getOrderedDate());
		dto.setDeliveryDate(orderDetails.getDeliveryDate());
		if (orderDetails.getProduct() != null) {
			dto.setProductId(orderDetails.getProduct().getProductId());
			dto.setProductName(orderDetails.getProduct().getProductName()); // Set the product name
		}
		return dto;
	}

	/**
	 * Maps an OrderDetailsDTO to its corresponding entity.
	 * 
	 * @param dto The OrderDetailsDTO to be mapped.
	 * @return The corresponding OrderDetails entity.
	 * @throws ResourceNotFoundException If the product referenced in the DTO is not
	 *                                   found.
	 */
	public OrderDetails mapToEntity(final OrderDetailsDTO dto) throws ResourceNotFoundException {
		if (dto == null) {
			return null;
		}
		final OrderDetails orderDetails = new OrderDetails();

		orderDetails.setOrderedQuantity(dto.getOrderedQuantity());

		if (dto.getProductId() != null) {
			orderDetails.setProduct(productRepository.findById(dto.getProductId()).orElseThrow(
					() -> new ResourceNotFoundException("Product not found with id: " + dto.getProductId())));

		}
		return orderDetails;
	}
}
