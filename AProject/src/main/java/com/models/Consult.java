package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="consult")

@DynamicUpdate
public class Consult {
	
	@Id
	@Column(name="consultid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int consultId;
	
	@Column(name="conname")
	private String conName;
	
	@Column(name="time")
	private String time;
	
	@Column(name="day")
	private String day;
	
	@Column(name="doctor")
	private String doctor;
	
	@Column(name="cost")
	private String cost;
	
	@Column(name="clinicid")
	@NotNull
	private int clinicId;
	
	public Consult(){
	}
	
	public Consult(String conName,String day, String time, String doctor, String cost, int clinicId) {
		super();
		
		this.conName = conName;
		this.time = time;
		this.day=day;
		this.doctor = doctor;
		this.cost = cost;
		this.clinicId = clinicId;
	}

	public int getConsultId() {
		return consultId;
	}

	public void setConsultId(int consultId) {
		this.consultId = consultId;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
}
