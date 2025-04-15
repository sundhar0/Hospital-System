package com.api.hospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.hospitalmanagement.model.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer>{

}
