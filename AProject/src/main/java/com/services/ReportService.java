package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepo;

	
	//get report by patient id
	public List<Report> getReportByPatient(int patientId){
		List<Report> report=new ArrayList<>();
		reportRepo.findByPatientId(patientId)
		.forEach(report::add);
		
		return report;
		
	}
	
	//get report by lab id
	public List<Report> getReportByLab(int labId){
		List<Report> report=new ArrayList<>();
		reportRepo.findByLabId(labId)
		.forEach(report::add);
		
		return report;
		
	}
	
	
	//add new report
	public void addReport(Report report) {
		reportRepo.save(report);
		
	}

	
	//get report by id
	public Report getOneReport(int id) {
		return reportRepo.findOne(id);
		
	}

	
	//delete report by id
	public void deleteReport(int id) {
		
		reportRepo.delete(id);
	}


	
}
