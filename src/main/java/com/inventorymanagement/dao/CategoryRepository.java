package com.inventorymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entity.Category;

/**
 * Repository interface for managing {@link Category} entities. This interface
 * extends {@link JpaRepository}, providing CRUD operations for {@link Category}
 * entities.
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
