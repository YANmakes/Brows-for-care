package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminrepo;
	
	public String getOneRole(int roleId){
		return "";
		
	}

	public Admin adminByName(String userName) {
		return adminrepo.findByName(userName);
	}

	public void addAdmin(Admin admin) {
		adminrepo.save(admin);
	
		
	}

}
