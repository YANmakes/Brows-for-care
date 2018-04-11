package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;




@Service
public class PatientService {

	@Autowired
	private PatientRepository patientrepo;
	
	public List<Patient> getAllPatients(){
		List<Patient> patient=new ArrayList<>();
		patientrepo.findAll()
		.forEach(patient::add);
		return patient;
	}
	

	public List<Patient> getValidPatients(int valid){
		List<Patient> patient=new ArrayList<>();
		patientrepo.findByValid(valid)
		.forEach(patient::add);
		return patient;
	}
	
	
	
	public void addPatient(Patient patient) {
		patientrepo.save(patient);
		
	}
	
	public Patient getOnePatient(int id){
		return patientrepo.findOne(id);
	}
	
	public Patient patientByName(String name){
		return patientrepo.findByName(name);
	}
	
	public Patient patientByNameAndAcive(String name,int active){
		return patientrepo.findByNameAndActive(name, active);
	}
	
	public Patient patientByEmail(String email){
		return patientrepo.findByEmail(email);
	}
	
	public void deleteOnePatient(int id){
		patientrepo.delete(id);
	}
	
	public int countAllPatients(){
		return patientrepo.countByValid(1);
	}
	
	public int countBlockedPatients(){
		return patientrepo.countByActiveAndValid(0, 1);
	}
}
