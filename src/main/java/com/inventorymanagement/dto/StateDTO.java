package com.inventorymanagement.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class StateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	Integer stateId;

	@NotBlank
	String stateName;

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
