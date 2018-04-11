package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;


@Repository
public interface TestRepository extends CrudRepository<Test,Integer> {

	public List<Test> findByLabId(int labid);
	
}
