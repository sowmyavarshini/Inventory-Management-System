package com.inventorymanagement.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.inventorymanagement.dao.CountryRepository;
import com.inventorymanagement.entity.Country;

public class CountryBO {
	@Autowired
	/* default */CountryRepository countryRepo;

	public List<Country> findCountries() {
		try {
			return countryRepo.findAll();
		} catch (DataAccessException e) {
			throw e;
		}
	}

}
