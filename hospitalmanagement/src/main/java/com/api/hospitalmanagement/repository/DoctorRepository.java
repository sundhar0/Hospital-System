package com.api.hospitalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hospitalmanagement.model.Doctor;
import com.api.hospitalmanagement.model.User;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{


	List<Doctor> findBydoctorId(int doctorId);

	Doctor findByUser(User user);

}
