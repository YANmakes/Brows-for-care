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
@Table(name="test")

@DynamicUpdate
public class Test {
	
	@Id
	@Column(name="testid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int testId;
	
	@Column(name="testname")
	private String testName;
	
	@Column(name="time")
	private String time;
	
	@Column(name="day")
	private String day;
	
	@Column(name="cost")
	private String cost;
	
	@Column(name="labid")
	@NotNull
	private int labId;
	
	public Test(){
	}

	
	public Test(String testName, String time, String day, String cost, int labId) {
		super();
		this.testName = testName;
		this.time = time;
		this.day = day;
		this.cost = cost;
		this.labId = labId;
	}


	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
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

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public int getLabId() {
		return labId;
	}

	public void setLabId(int labId) {
		this.labId = labId;
	}
	
	
	

}
