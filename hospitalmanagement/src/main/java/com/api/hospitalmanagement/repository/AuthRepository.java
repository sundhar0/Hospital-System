package com.api.hospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hospitalmanagement.model.User;

public interface AuthRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
