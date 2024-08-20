package com.inventorymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.inventorymanagement.bo.CityBO;
import com.inventorymanagement.entity.City;

public class CityService {

	@Autowired
	/* default */CityBO cityBO = null;

	public List<City> findCities() {
		return cityBO.findCities();

	}

}
