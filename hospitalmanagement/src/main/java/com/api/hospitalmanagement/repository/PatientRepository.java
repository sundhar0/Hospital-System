package com.api.hospitalmanagement.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hospitalmanagement.model.Doctor;
import com.api.hospitalmanagement.model.Patient;
import com.api.hospitalmanagement.model.User;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

	List<Patient> findByDoctors(Doctor doctor);

	Patient findByUser(User user);

}
