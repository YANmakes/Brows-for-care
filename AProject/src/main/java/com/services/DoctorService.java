package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;


@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorrepo;
	
	//to get all doctors
	public List<Doctor> getAllDoctors(){
		
		List<Doctor> doctor=new ArrayList<>();
		doctorrepo.findAll()
		.forEach(doctor::add);
		return doctor;
	}
	
	
	
	//Get Active and valid doctors
	public List<Doctor> getValidDoctors(){
		
		List<Doctor> doctor=new ArrayList<>();
		doctorrepo.findByActiveAndValid(1,1)
		.forEach(doctor::add);
		return doctor;
	}
	
	
	
	//get only valid doctors
	public List<Doctor> validDoctors(){
		
		List<Doctor> doctor=new ArrayList<>();
		doctorrepo.findByValid(1)
		.forEach(doctor::add);
		return doctor;
	}
	
	
	//to save doctor
	public void addDoctor(Doctor doctor) {
		doctorrepo.save(doctor);
		
	}
	
	//find a doctor by id
	public Doctor getOneDoctor(int id){
		return doctorrepo.findOne(id);
	}
	
	//find by name
	public Doctor doctorByName(String userName) {
		return doctorrepo.findByName(userName);
		
	}
	
	
	//find by mail
	public Doctor doctorByEmail(String email) {
		return doctorrepo.findByEmail(email);
		
	}
	
	//delete by id
	public void deleteOneDoctor(int id){
		 doctorrepo.delete(id);
	}
	
	
	//count valid doctors
	public int countAllDoctors(){
		return doctorrepo.countByValid(1);
	}
	
	//count blocked doctord
	public int countBlockedDoctors(){
		return doctorrepo.countByActiveAndValid(0, 1);
	}
	


}
