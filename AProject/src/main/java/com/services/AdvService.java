package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class AdvService {

	@Autowired
	private AdvRepository advRepo;
	
	//This method for getting all active advertisments for users
	public List<Adv> getAdvs(){
		List<Adv> adv=new ArrayList<>();
		advRepo.findByActive(1)
		.forEach(adv::add);
		
		return adv;
		
	}

	public List<Adv> getAdvBySender(int sender,int role){
		List<Adv> adv=new ArrayList<>();
		advRepo.findBySenderAndSenderRole(sender, role)
		.forEach(adv::add);
		
		return adv;
		
	}
	
	public List<Adv> getAllAdvs(){
		List<Adv> adv=new ArrayList<>();
		advRepo.findAll()
		.forEach(adv::add);
		
		return adv;
		
	}
	
	public List<Adv> getActiveAdvs(int active){
		List<Adv> adv=new ArrayList<>();
		advRepo.findByActive(active)
		.forEach(adv::add);
		
		return adv;
		
	}
	
	public void addAdv(Adv adv) {
		advRepo.save(adv);
		
	}

	public Adv getOneAdv(int id) {
		return advRepo.findOne(id);
		
	}

	public void deleteAdv(int id) {
		
		advRepo.delete(id);
	}


	
}
