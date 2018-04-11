package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;
@Repository
public interface PatientRepository extends CrudRepository<Patient,Integer>{

	public Patient findByName(String name);
	
	public Patient findByEmail(String email);
	
	public Patient findByNameAndActive(String name,int active);
	
	public int countByActiveAndValid(int active,int valid);
	
	public int countByValid(int valid);
	
	public List<Patient> findByActiveAndValid(int active,int valid);

	public List<Patient> findByValid(int valid);
	
	
}
