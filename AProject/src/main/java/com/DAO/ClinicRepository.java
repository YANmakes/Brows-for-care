package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;
@Repository
public interface ClinicRepository extends CrudRepository<Clinic,Integer>{

	public Clinic findByName(String userName);
	
	public Clinic findByEmail(String email);
	
	public int countByActiveAndValid(int active,int valid);
	
	public int countByValid(int valid);
	
	public List<Clinic> findByActiveAndValid(int active,int valid);

	public List<Clinic> findByValid(int valid);
	

}
