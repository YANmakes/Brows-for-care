package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="clinic")
public class Clinic {
	
	@Id
	@Column(name="clinicid",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int clinicId;
	
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
	private double rating=2.0;
	
	@Column(name="slmc")
	private String slmc;
	
	@Column(name="location")
	private String location;
	
	@Column(name="role")
	private int role=5;
	
	@Column(name="active")
	private int active=1;
	
	@Column(name="valid")
	private int valid;
	
	//Default Constructor
	public Clinic(){
		
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
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

	public String getSlmc() {
		return slmc;
	}

	public void setSlmc(String slmc) {
		this.slmc = slmc;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
