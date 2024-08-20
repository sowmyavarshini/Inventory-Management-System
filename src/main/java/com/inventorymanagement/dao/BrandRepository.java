package com.inventorymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entity.Brand;

/**
 * Repository interface for managing {@link Brand} entities. This interface
 * extends {@link JpaRepository}, providing CRUD operations for {@link Brand}
 * entities.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
