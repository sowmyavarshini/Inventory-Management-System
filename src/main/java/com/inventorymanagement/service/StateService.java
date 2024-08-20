package com.inventorymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.inventorymanagement.bo.StateBO;
import com.inventorymanagement.entity.State;

public class StateService {
	@Autowired
	/* default */StateBO stateBO = null;

	public List<State> findStates() {
		return stateBO.findStates();

	}

}
