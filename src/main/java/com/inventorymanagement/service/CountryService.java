package com.inventorymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.inventorymanagement.bo.CountryBO;
import com.inventorymanagement.entity.Country;

public class CountryService {

	@Autowired
	/* default */CountryBO countryBO = null;

	public List<Country> findCountries() {
		return countryBO.findCountries();

	}

}
