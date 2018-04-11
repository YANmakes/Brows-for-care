package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;


@Service
public class LoginService {

	@Autowired
	private LoginRepository loginrepo;
	
	public List<Login> getAlllogins(){
		
		List<Login> login=new ArrayList<>();
		loginrepo.findAll()
		.forEach(login::add);
		return login;
	}
	
	public void addLogin(Login login) {
		loginrepo.save(login);
		
	}
	
	public  Login getOneLogin(String id){
		return loginrepo.findOne(id);
	}
	
	public  Login loginByEmail(String email){
		return loginrepo.findByEmail(email);
	}
	
	
	/*public  Login getValidation(String userName,String password){
		return loginrepo.findByUserNameAndPasswordAndActive(userName, password, 1);
	}*/

	
	public  Login getValidation(String email,String password){
		return loginrepo.findByEmailAndPasswordAndActive(email, password, 1);
	}
}
