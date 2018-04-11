package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class RoleService {

	@Autowired
	private RoleRepository rolerepo;
	
	public String getOneRole(int roleId){
		return rolerepo.findOne(roleId).getRoleName();
		
	}

}
