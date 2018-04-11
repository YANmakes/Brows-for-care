package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;
@Repository
public interface PharmacyRepository extends CrudRepository<Pharmacy,Integer>{

	Pharmacy findByName(String userName);
	
	public Pharmacy findByEmail(String email);
	
	public int countByActiveAndValid(int active,int valid);

	public int countByValid(int valid);
	
	public List<Pharmacy> findByActiveAndValid(int active,int valid);

	public List<Pharmacy> findByValid(int valid);

}