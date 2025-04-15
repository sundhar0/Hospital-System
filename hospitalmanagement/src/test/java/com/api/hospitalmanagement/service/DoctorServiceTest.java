package com.api.hospitalmanagement.service;

import com.api.hospitalmanagement.enums.Speciality;
import com.api.hospitalmanagement.exception.InvalidIDException;
import com.api.hospitalmanagement.model.Doctor;
import com.api.hospitalmanagement.model.Patient;
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

    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        doctor = new Doctor();
        doctor.setDoctorId(1);
        doctor.setName("Dr. John");
        doctor.setSpeciality(Speciality.GYNAC);

        patient = new Patient();
        patient.setId(101);
        patient.setName("Alice");
    }

    @Test
    public void testGetAllPatientByDocId_Success() throws InvalidIDException {
        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        when(patientRepository.findByDoctors(doctor)).thenReturn(List.of(patient));

        List<Patient> result = doctorService.getAllPatientByDocId(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    public void testGetAllPatientByDocId_InvalidId() {
        when(doctorRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> {
            doctorService.getAllPatientByDocId(99);
        });
    }
}
