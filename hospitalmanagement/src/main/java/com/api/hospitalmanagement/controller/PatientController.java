package com.api.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hospitalmanagement.exception.InvalidIDException;
import com.api.hospitalmanagement.model.Patient;
import com.api.hospitalmanagement.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping("/add")
	public String add(@RequestBody Patient patient) throws InvalidIDException{
		return patientService.add(patient);
	}
	
	
}
