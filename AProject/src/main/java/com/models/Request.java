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
@Table(name="request")
public class Request {

	@Id
	@Column(name="requestid",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int requestId;
	
	@Column(name="sender")
	@NotNull
	private int sender;
	
	@Column(name="receiver")
	@NotNull
	private int receiver;
	
	@Column(name="receiverrole")
	@NotNull
	private int receiverRole;
	
	@Column(name="datetime")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date datetime;
	
	@Column(name="content")
	private String content;
	
	@Column(name="seen")
	private int seen=0;
	
	public Request(){
	}

	public Request( int sender, int receiver,int role,String content) {
		super();
		Date now = new Date();
		this.sender = sender;
		this.receiver = receiver;
		this.receiverRole=role;
		this.datetime=now;
		this.content=content;
		
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	
	public java.util.Date getDatetime() {
		return datetime;
	}

	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public int getReceiverRole() {
		return receiverRole;
	}

	public void setReceiverRole(int receiverRole) {
		this.receiverRole = receiverRole;
	}

	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}


	
	
	
}
