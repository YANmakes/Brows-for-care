package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="doctor")
public class Doctor {

	@Id
	@Column(name="doctorid",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int doctorId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="email",unique=true)
	@NotNull
	private String email;
	
	@Column(name="password")
	@NotNull
	private String password;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="rating")
	private double rating=3.5;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="specialization")
	private String specialization;
	
	@Column(name="slmc")
	private String slmc;
	
	@Column(name="hospital")
	private String hospital;
	
	@Column(name="role")
	private int role=4;
	

	@Column(name="active")
	private int active=1;
	
	@Column(name="valid")
	private int valid=0;
	
	public Doctor(){
		
	}
	
	
	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getSlmc() {
		return slmc;
	}

	public void setSlmc(String slmc) {
		this.slmc = slmc;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public int getValid() {
		return valid;
	}


	public void setValid(int valid) {
		this.valid = valid;
	}
	
	
	
	

}
