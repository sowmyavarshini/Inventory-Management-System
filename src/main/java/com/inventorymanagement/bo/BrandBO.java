package com.inventorymanagement.bo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.inventorymanagement.dao.BrandRepository;
import com.inventorymanagement.entity.Brand;
import com.inventorymanagement.exception.ResourceNotFoundException;

/**
 * Business Object (BO) for handling operations related to brands. Provides
 * methods for managing brand records, including insertion and retrieval.
 */
@Component
public class BrandBO {

	/**
	 * Repository instance used to perform CRUD operations on brand entities. It is
	 * automatically injected by Spring's dependency injection mechanism.
	 */
	@Autowired
	private BrandRepository brandRepository;

	/**
	 * Inserts a new brand into the repository.
	 * 
	 * @param brand the brand to be inserted.
	 * @return the inserted brand.
	 * @throws DataIntegrityViolationException if there is a constraint violation.
	 */
	public Brand insert(final Brand brand) {
		try {
			return brandRepository.save(brand);
		} catch (DataIntegrityViolationException e) {
			throw e;
		}
	}

	/**
	 * Finds a brand by its ID.
	 * 
	 * @param brandId the ID of the brand to be found.
	 * @return the found brand.
	 * @throws ResourceNotFoundException if the brand is not found.
	 */
	public Brand findBrand(final int brandId) throws ResourceNotFoundException {
		try {
			final Optional<Brand> brand = brandRepository.findById(brandId);
			return brand.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + brandId));
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds all brands.
	 * 
	 * @return a list of all brands.
	 * @throws DataAccessException if there is an issue accessing the data.
	 */
	public List<Brand> findBrands() {
		try {
			return brandRepository.findAll();
		} catch (DataAccessException e) {
			throw e;
		}
	}
}
