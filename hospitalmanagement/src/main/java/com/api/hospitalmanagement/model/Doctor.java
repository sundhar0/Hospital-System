package com.api.hospitalmanagement.model;

import com.api.hospitalmanagement.enums.Speciality;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class Doctor {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;
	
    @Column(nullable = false)
	private String name;
	
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    
    @OneToOne
    private User user;

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
