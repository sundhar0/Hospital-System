package com.api.hospitalmanagement.service;

import com.api.hospitalmanagement.exception.InvalidIDException;
import com.api.hospitalmanagement.model.*;
import com.api.hospitalmanagement.repository.DoctorRepository;
import com.api.hospitalmanagement.repository.PatientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private AuthService authService;

    private Doctor doctor;
    private User user;
    private Patient patient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUserId(101);
        user.setUserName("docuser");

        doctor = new Doctor();
        doctor.setDoctorId(1);
        doctor.setName("Dr. John");
        doctor.setUser(user);

        patient = new Patient();
        patient.setId(1);
        patient.setName("Patient A");
        patient.setDoctors(List.of(doctor));
    }

    @Test
    public void testAddDoctor_Success() throws InvalidIDException {
        when(authService.getById(101)).thenReturn(user);
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        String result = doctorService.add(101, doctor);

        assertEquals("doctor added successfully", result);
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    public void testGetAllPatientByDocId_Success() throws InvalidIDException {
        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        when(patientRepository.findByDoctors(doctor)).thenReturn(List.of(patient));

        List<Patient> result = doctorService.getAllPatientByDocId(1);

        assertEquals(1, result.size());
        assertEquals("Patient A", result.get(0).getName());
        verify(patientRepository, times(1)).findByDoctors(doctor);
    }

    @Test
    public void testGetAllPatientByDocId_InvalidId() {
        when(doctorRepository.findById(99)).thenReturn(Optional.empty());

        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
            doctorService.getAllPatientByDocId(99);
        });

        assertEquals("Doctor ID NOT FOUND", exception.getMessage());
    }
}
