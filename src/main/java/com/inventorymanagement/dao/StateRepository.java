package com.inventorymanagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventorymanagement.entity.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	Optional<State> findByStateName(String stateName);

	boolean existsByStateName(String stateName);
}
