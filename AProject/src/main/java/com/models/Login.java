package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="login")
public class Login {

	@Id
	@Column(name="username",unique=true)
	@NotNull
	private String userName;
	
	@Column(name="password")
	@NotNull
	private String password;
	
	@Column(name="email")
	@NotNull
	private String email;

	@Column(name="role")
	@NotNull
	private int role;

	@Column(name="active")
	@NotNull
	private int active=1;

	public Login(String userName,String password,String email,int role){
		
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.role=role;
	}
	
	public Login(String userName,String password,String email,int role,int active){
		
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.role=role;
		this.active=active;
	}
	public Login(){
		
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	
	
}
