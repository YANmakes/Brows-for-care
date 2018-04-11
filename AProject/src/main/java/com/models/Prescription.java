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
@Table(name="prescription")
public class Prescription {

	@Id
	@Column(name="prescid",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int prescId;
	
	@Column(name="patientid")
	@NotNull
	private int patientId;
	
	@Column(name="pharmid")
	@NotNull
	private int pharmId;
	
	@Column(name="filename")
	@NotNull
	private String filename;
	
	@Column(name="location")
	@NotNull
	private String location;
	
	@Column(name="reply")
	private String reply;
	
	@Column(name="datetime")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date datetime;
	
	@Column(name="arrival")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date arrival;
	
	@Column(name="useen")
	@NotNull
	private int useen=1;
	
	@Column(name="pseen")
	@NotNull
	private int pseen=0;
	
	public Prescription(){
	}

	public Prescription( int patientId, int pharmId,String filename,String location) {
		super();
		Date now = new Date();
		
		this.patientId = patientId;
		this.pharmId = pharmId;
		this.filename=filename;
		this.location=location;
		this.datetime=now;
		
	}

	public int getPrescId() {
		return prescId;
	}

	public void setPrescId(int prescId) {
		this.prescId = prescId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getPharmId() {
		return pharmId;
	}

	public void setPharmId(int pharmId) {
		this.pharmId = pharmId;
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

	public int getUseen() {
		return useen;
	}

	public void setUseen(int useen) {
		this.useen = useen;
	}

	public int getPseen() {
		return pseen;
	}

	public void setPseen(int pseen) {
		this.pseen = pseen;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public java.util.Date getArrival() {
		return arrival;
	}

	public void setArrival(java.util.Date arrival) {
		this.arrival = arrival;
	}

	
	
	
	
}
