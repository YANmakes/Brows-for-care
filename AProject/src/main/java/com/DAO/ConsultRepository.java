package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;


@Repository
public interface ConsultRepository extends CrudRepository<Consult,Integer> {

	public List<Consult> findByClinicId(int clinicid);
	
}
