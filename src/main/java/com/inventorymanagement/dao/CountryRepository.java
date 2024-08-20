package com.inventorymanagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventorymanagement.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	Optional<Country> findByCountryName(String countryName);

	boolean existsByCountryName(String cityName);
}
