package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;


@Service
public class PharmacyService {

	@Autowired
	private PharmacyRepository pharmcyrepo;
	
	public List<Pharmacy> getAllPharmacy(){
		
		List<Pharmacy> pharmacy=new ArrayList<>();
		pharmcyrepo.findAll()
		.forEach(pharmacy::add);
		return pharmacy;
	}
	
	
	public List<Pharmacy> getValidPharmacy(){
		
		List<Pharmacy> pharmacy=new ArrayList<>();
		pharmcyrepo.findByActiveAndValid(1,1)
		.forEach(pharmacy::add);
		return pharmacy;
	}
	
	public List<Pharmacy> validPharmacy(){
		
		List<Pharmacy> pharmacy=new ArrayList<>();
		pharmcyrepo.findByValid(1)
		.forEach(pharmacy::add);
		return pharmacy;
	}

	public void addPharmacy(Pharmacy pharmacy) {
		pharmcyrepo.save(pharmacy);
		
	}
	
	public Pharmacy getOnePharmacy(int id){
		return pharmcyrepo.findOne(id);
	}
	

	public Pharmacy pharmacyByName(String userName) {
		// TODO Auto-generated method stub
		return pharmcyrepo.findByName(userName);
	}
	
	public Pharmacy pharmacyByEmail(String email) {
		
		return pharmcyrepo.findByEmail(email);
	}

	
	public void deleteOnePharmacy(int id){
		 pharmcyrepo.delete(id);
	}
	
	public int countAllPharmacies(){
		return pharmcyrepo.countByValid(1);
	}
	
	public int countBlockedPharmacies(){
		return pharmcyrepo.countByActiveAndValid(0, 1);
	}

}
