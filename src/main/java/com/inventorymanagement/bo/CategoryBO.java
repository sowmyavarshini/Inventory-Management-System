package com.inventorymanagement.bo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.inventorymanagement.dao.CategoryRepository;
import com.inventorymanagement.entity.Category;
import com.inventorymanagement.exception.ResourceNotFoundException;

/**
 * Business Object (BO) for handling operations related to categories. Provides
 * methods for managing category records, including insertion and retrieval.
 */
@Component
public class CategoryBO {

	/**
	 * Repository instance used to perform CRUD operations on Category entities. It
	 * is automatically injected by Spring's dependency injection mechanism.
	 */
	@Autowired
	/* default */CategoryRepository categoryRepo;

	/**
	 * Inserts a new category into the repository.
	 * 
	 * @param category the category to be inserted.
	 * @return the inserted category.
	 * @throws DataIntegrityViolationException if there is a constraint violation.
	 */
	public Category insert(final Category category) {
		try {
			return categoryRepo.save(category);
		} catch (DataIntegrityViolationException e) {
			throw e;
		}
	}

	/**
	 * Finds a category by its ID.
	 * 
	 * @param categoryId the ID of the category to be found.
	 * @return the found category.
	 * @throws ResourceNotFoundException if the category is not found.
	 */
	public Category findCategory(final int categoryId) throws ResourceNotFoundException {
		try {
			final Optional<Category> category = categoryRepo.findById(categoryId);
			return category
					.orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * Finds all categories.
	 * 
	 * @return a list of all categories.
	 * @throws DataAccessException if there is an issue accessing the data.
	 */
	public List<Category> findCategories() {
		try {
			return categoryRepo.findAll();
		} catch (DataAccessException e) {
			throw e;
		}
	}
}
