package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class ConsultService {

	@Autowired
	private ConsultRepository consultrepo;
	

	public List<Consult> getConsults(int clinicid){
		List<Consult> consults=new ArrayList<>();
		consultrepo.findByClinicId(clinicid)
		.forEach(consults::add);
		
		return consults;
		
	}


	public void addConsult(Consult con) {
		consultrepo.save(con);
		
	}


	public void deleteConsult(int consultid) {
		
		consultrepo.delete(consultid);
	}


	
}
