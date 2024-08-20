package com.inventorymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventorymanagement.bo.BrandBO;
import com.inventorymanagement.entity.Brand;
import com.inventorymanagement.exception.ResourceNotFoundException;

/**
 * Service class responsible for handling brand-related operations. It interacts
 * with the BrandBO to perform business logic related to brands.
 */
@Component
public class BrandService {

	/**
	 * Business object (BO) used for brand operations. Injected by Spring through
	 * the @Autowired annotation.
	 */
	@Autowired
	/* default */BrandBO brandBO = null;

	/**
	 * Constructor for BrandService.
	 * 
	 * @param bo The BrandBO instance to be used by this service.
	 */
	public BrandService(final BrandBO brandBO) {
		this.brandBO = brandBO;
	}

	/**
	 * Inserts a new brand into the system.
	 * 
	 * @param p The brand to be inserted.
	 * @return The inserted brand.
	 */
	public Brand insert(final Brand brand) {
		return brandBO.insert(brand);
	}

	/**
	 * Finds a brand by its ID.
	 * 
	 * @param id The ID of the brand to be found.
	 * @return The brand with the specified ID.
	 * @throws ResourceNotFoundException If the brand is not found.
	 */
	public Brand findBrand(final int brandId) throws ResourceNotFoundException {
		return brandBO.findBrand(brandId);

	}

	/**
	 * Finds all brands in the system.
	 * 
	 * @return A list of all brands.
	 */
	public List<Brand> findBrands() {
		return brandBO.findBrands();

	}

}
