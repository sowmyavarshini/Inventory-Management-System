package com.inventorymanagement.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.inventorymanagement.dao.CityRepository;
import com.inventorymanagement.entity.City;

public class CityBO {

	@Autowired
	/* default */CityRepository cityRepo;

	public List<City> findCities() {
		try {
			return cityRepo.findAll();
		} catch (DataAccessException e) {
			throw e;
		}
	}

}
