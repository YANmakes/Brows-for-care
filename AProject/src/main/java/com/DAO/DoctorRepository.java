package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;


@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Integer>{
	
	public Doctor findByName(String userName);
	
	public Doctor findByEmail(String email);
	
	public int countByActiveAndValid(int active,int valid);
	
	public int countByValid(int valid);
	
	public List<Doctor> findByActiveAndValid(int active,int valid);
	
	public List<Doctor> findByValid(int valid);

}
