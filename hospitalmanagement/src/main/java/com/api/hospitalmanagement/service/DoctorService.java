package com.api.hospitalmanagement.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hospitalmanagement.repository.DoctorRepository;
import com.api.hospitalmanagement.repository.PatientRepository;
import com.api.hospitalmanagement.exception.InvalidIDException;
import com.api.hospitalmanagement.model.*;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AuthService authService;
	
	public List<Patient> getAllPatientByDocId(int doctorId) throws InvalidIDException{
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if(doctor.isEmpty())
			throw new InvalidIDException("Doctor ID NOT FOUND");
		
		return patientRepository.findByDoctors(doctor.get());
	}


	public String add(int userId, Doctor doctor) throws InvalidIDException {
		User user = authService.getById(userId);
		Doctor existingDoc = doctorRepository.findByUser(user);
		if(existingDoc != null)
			throw new InvalidIDException("Doctor already exist");
		doctor.setUser(user);
		doctorRepository.save(doctor);
		return "doctor added successfully";
	}
}
