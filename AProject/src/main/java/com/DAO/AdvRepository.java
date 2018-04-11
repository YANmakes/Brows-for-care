package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;


@Repository
public interface AdvRepository extends CrudRepository<Adv,Integer> {

	public List<Adv> findByActive(int active);
	
	public List<Adv> findBySenderAndSenderRole(int sender,int role);

	
}
