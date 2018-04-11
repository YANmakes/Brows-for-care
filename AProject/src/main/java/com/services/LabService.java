package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class LabService {

	@Autowired
	private LabRepository labrepo;
	
	
	//method for get all  Lab details
		public List<Lab> getAllULabs(){
			
			List<Lab> labs=new ArrayList<>();
			labrepo.findAll()
			.forEach(labs::add);
			
			return labs;
			
		}
		
		
		

		//method for get all active and valid Lab details
				public List<Lab> getValidULabs(){
					
					List<Lab> labs=new ArrayList<>();
					labrepo.findByActiveAndValid(1,1)
					.forEach(labs::add);
					
					return labs;
					
				}
				
				
				
				public List<Lab> valiLabs(){
					
					List<Lab> lab=new ArrayList<>();
					labrepo.findByValid(1)
					.forEach(lab::add);
					return lab;
				}	
		
	
	public void addLab(Lab lab) {
		labrepo.save(lab);
		
	}
	
	//method for get one lab details
	public Lab getOneLab(int id){
		
		return labrepo.findOne(id);
	}


	public Lab labByName(String userName) {
		return labrepo.findByName(userName);
		
	}
	
	public Lab labByEmail(String email) {
		return labrepo.findByEmail(email);
		
	}
	
	public void deleteOneLab(int id){
		 labrepo.delete(id);
	}

	
	public int countAllLabs(){
		return labrepo.countByValid(1);
	}
	
	public int countBlockedLabs(){
		return labrepo.countByActiveAndValid(0, 1);
	}
	
}
