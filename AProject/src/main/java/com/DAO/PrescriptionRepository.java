package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;


@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription,Integer> {

	public List<Prescription> findByPatientIdAndUseen(int patientId, int useen);

	public List<Prescription> findByPharmIdAndPseen(int pharmId, int pseen);

	
}
