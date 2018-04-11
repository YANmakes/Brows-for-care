package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;




@Service
public class ClinicService {
	
	@Autowired
	private ClinicRepository clinicrepo;
	
	
	//method for get all the clinic details
		public List<Clinic> getAllClinics(){
			
			List<Clinic> clinic=new ArrayList<>();
			clinicrepo.findAll()
			.forEach(clinic::add);
			
			return clinic;
			
		}
		
		//method for get all valid and active clinic details
				public List<Clinic> getValidClinics(){
					
					List<Clinic> clinic=new ArrayList<>();
					clinicrepo.findByActiveAndValid(1,1)
					.forEach(clinic::add);
					
					return clinic;
					
				}
				
				public List<Clinic> validClinics(){
					
					List<Clinic> clinic=new ArrayList<>();
					clinicrepo.findByValid(1)
					.forEach(clinic::add);
					return clinic;
				}	
		
	public void addClinic(Clinic clinic) {
		clinicrepo.save(clinic);
		
	}
	
	//method for get one clinic detail
		public Clinic getOneClinic(int id){
			return clinicrepo.findOne(id);
		}
		
		public Clinic clinicByName(String userName) {
			return clinicrepo.findByName(userName);
			
		}
		
		public Clinic clinicByEmail(String email) {
			return clinicrepo.findByEmail(email);
			
		}
		
		public void deleteOneClinic(int id){
			 clinicrepo.delete(id);
		}
		
		
		public int countAllClinic(){
			return clinicrepo.countByValid(1);
		}
		
		public int countBlockedClinics(){
			 return clinicrepo.countByActiveAndValid(0, 1);
		}
		

}
