package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepo;
	

	public List<Prescription> getPrescByPatient(int patientId,int useen){
		List<Prescription> prescription=new ArrayList<>();
		prescriptionRepo.findByPatientIdAndUseen(patientId,useen)
		.forEach(prescription::add);
		
		return prescription;
		
	}

	public List<Prescription> getPrescByPharmacy(int pharmId,int pseen){
		List<Prescription> prescription=new ArrayList<>();
		prescriptionRepo.findByPharmIdAndPseen(pharmId,pseen)
		.forEach(prescription::add);
		
		return prescription;
		
	}

	
	
	public void addPresc(Prescription presc) {
		prescriptionRepo.save(presc);
		
	}

	public Prescription getOnePresc(int id) {
		return prescriptionRepo.findOne(id);
		
	}

	public void deletePresc(int prescId) {
		
		prescriptionRepo.delete(prescId);
	}


	
}
