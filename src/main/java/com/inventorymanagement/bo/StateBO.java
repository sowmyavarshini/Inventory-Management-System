package com.inventorymanagement.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.inventorymanagement.dao.StateRepository;
import com.inventorymanagement.entity.State;

public class StateBO {

	@Autowired
	/* default */StateRepository stateRepo;

	public List<State> findStates() {
		try {
			return stateRepo.findAll();
		} catch (DataAccessException e) {
			throw e;
		}
	}

}
