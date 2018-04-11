package com.models;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="report")
public class Report {

	@Id
	@Column(name="reportid",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int reportId;
	
	@Column(name="patientid")
	@NotNull
	private int patientId;
	
	@Column(name="labid")
	@NotNull
	private int labId;
	
	@Column(name="filename")
	@NotNull
	private String filename;
	
	@Column(name="location")
	@NotNull
	private String location;
	
	@Column(name="datetime")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date datetime;
	
	public Report(){
	}

	public Report( int patientId, int labId,String filename,String location) {
		super();
		Date now = new Date();
		
		this.patientId = patientId;
		this.labId = labId;
		this.filename=filename;
		this.location=location;
		this.datetime=now;
		
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getLabId() {
		return labId;
	}

	public void setLabId(int labId) {
		this.labId = labId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public java.util.Date getDatetime() {
		return datetime;
	}

	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}

	
	
	
	
}
