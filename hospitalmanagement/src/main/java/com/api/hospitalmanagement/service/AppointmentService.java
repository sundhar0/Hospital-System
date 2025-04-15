package com.api.hospitalmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hospitalmanagement.exception.InvalidIDException;
import com.api.hospitalmanagement.model.Doctor;
import com.api.hospitalmanagement.model.Patient;
import com.api.hospitalmanagement.repository.DoctorRepository;
import com.api.hospitalmanagement.repository.PatientRepository;

@Service
public class AppointmentService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public String add(int patientId, int doctorId) throws InvalidIDException {
		Optional<Patient> patient = patientRepository.findById(patientId);
		List<Doctor> doctor = doctorRepository.findBydoctorId(doctorId);
		
		if(patient.isEmpty() || doctor.isEmpty())
			throw new InvalidIDException("Invalid Patient or doctor Id");
		
		patient.get().setDoctors(doctor);
        patientRepository.save(patient.get());
        
        return "Appointment added";
	}
}
