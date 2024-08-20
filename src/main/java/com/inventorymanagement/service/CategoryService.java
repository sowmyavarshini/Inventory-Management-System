package com.inventorymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventorymanagement.bo.CategoryBO;
import com.inventorymanagement.entity.Category;
import com.inventorymanagement.exception.ResourceNotFoundException;

/**
 * Service class responsible for handling category-related operations. It
 * interacts with the CategoryBO to perform business logic related to
 * categories.
 */
@Component
public class CategoryService {

	/**
	 * Business object (BO) used for category operations. Injected by Spring through
	 * the @Autowired annotation.
	 */
	@Autowired
	/* default */CategoryBO categoryBO = null;

	/**
	 * Constructor for CategoryService.
	 * 
	 * @param categoryBO The CategoryBO instance to be used by this service.
	 */
	public CategoryService(final CategoryBO categoryBO) {
		this.categoryBO = categoryBO;
	}

	/**
	 * Inserts a new category into the system.
	 * 
	 * @param category The category to be inserted.
	 * @return The inserted category.
	 */
	public Category insert(final Category category) {
		return categoryBO.insert(category);
	}

	/**
	 * Finds a category by its ID.
	 * 
	 * @param categoryId The ID of the category to be found.
	 * @return The category with the specified ID.
	 * @throws ResourceNotFoundException If the category is not found.
	 */
	public Category findCategory(final int categoryId) throws ResourceNotFoundException {
		return categoryBO.findCategory(categoryId);

	}

	/**
	 * Finds all categories in the system.
	 * 
	 * @return A list of all categories.
	 */
	public List<Category> findCategories() {
		return categoryBO.findCategories();

	}

}
