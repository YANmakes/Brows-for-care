package com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.models.*;


@Repository
public interface ReportRepository extends CrudRepository<Report,Integer> {

	public List<Report> findByPatientId(int patientId);

	public List<Report> findByLabId(int labId);

	
}
