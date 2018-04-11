package com.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;
@Repository
public interface LoginRepository extends CrudRepository<Login,String>{
	
	public Login findByUserNameAndPasswordAndActive(String userName,String password,int active);
	
	public Login findByUserName(String userName);
	
	public Login findByEmail(String email);

	public Login findByEmailAndPasswordAndActive(String email, String password, int active);

}
