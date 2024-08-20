package com.inventorymanagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventorymanagement.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	Optional<City> findByCityName(String cityName);

	boolean existsByCityName(String cityName);

}
