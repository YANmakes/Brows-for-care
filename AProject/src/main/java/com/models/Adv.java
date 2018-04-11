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
@Table(name="adv")
public class Adv {

	@Id
	@Column(name="advid",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int advId;
	
	@Column(name="sender")
	@NotNull
	private int sender;
	
	@Column(name="senderrole")
	@NotNull
	private int senderRole;
	
	@Column(name="adname")
	@NotNull
	private String adName;
	
	@Column(name="location")
	@NotNull
	private String location;
	
	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date datetime;

	@Column(name="active")
	@NotNull
	private int active=0;
	
	
	
	public Adv(){
	}

	public Adv( int sender, int senderRole,String adName,String location) {
		super();
		Date now = new Date();
		
		this.sender = sender;
		this.senderRole = senderRole;
		this.adName=adName;
		this.location=location;
		this.datetime=now;
		
	}

	public int getAdvId() {
		return advId;
	}

	public void setAdvId(int advId) {
		this.advId = advId;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getSenderRole() {
		return senderRole;
	}

	public void setSenderRole(int senderRole) {
		this.senderRole = senderRole;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	
	
	
	
	
}
