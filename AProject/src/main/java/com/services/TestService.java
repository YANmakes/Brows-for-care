package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class TestService {

	@Autowired
	private TestRepository testrepo;
	

	public List<Test> getTests(int labid){
		List<Test> tests=new ArrayList<>();
		testrepo.findByLabId(labid)
		.forEach(tests::add);
		
		return tests;
		
	}
	
	public void addTest(Test test){
		testrepo.save(test);
		
	}
	
	public void deleteTest(int testid){
		testrepo.delete(testid);
		
	}
}
