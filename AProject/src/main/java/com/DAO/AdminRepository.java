package com.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer>{

	public Admin findByName(String userName);

}
