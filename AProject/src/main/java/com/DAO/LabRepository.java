package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;
@Repository
public interface LabRepository extends CrudRepository<Lab,Integer>{

	public Lab findByName(String userName);
	
	public Lab findByEmail(String email);
	
	public int countByActiveAndValid(int active,int valid);
	
	public int countByValid(int valid);
	
	public List<Lab> findByActiveAndValid(int active,int valid);

	public List<Lab> findByValid(int valid);

}
