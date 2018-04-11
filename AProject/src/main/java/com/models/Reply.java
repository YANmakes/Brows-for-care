package com.models;


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
@Table(name="reply")
public class Reply {

	@Id
	@Column(name="replyid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int replyId;
	
	@Column(name="sender")
	@NotNull
	private int sender;
	
	@Column(name="receiver")
	@NotNull
	private int receiver;
	
	@Column(name="senderrole")
	@NotNull
	private int senderRole;
	
	@Column(name="content")
	private String content;
	
	@Column(name="status")
	private String status;
	
	@Column(name="datetime")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date datetime;
	
	@Column(name="requestid")
	@NotNull
	private int requestId;
	
	@Column(name="seen")
	private int seen=0;
	
	@Column(name="type")
	private String type;
	
	public Reply(){
	}

	public Reply( int sender, int receiver,String status,String content,int requestId) {
		super();
		Date now = new Date();
		this.sender = sender;
		this.receiver = receiver;
		this.content=content;
		this.status=status;
		this.datetime=now;
		this.requestId=requestId;
		this.type=type;
	
	}
	
	public Reply(int replyId,int sender, int receiver,int senderRole,String status,java.util.Date datetime,String content,int requestId) {
		super();
		this.replyId=replyId;
		this.sender = sender;
		this.receiver = receiver;
		this.senderRole=senderRole;
		this.content=content;
		this.status=status;
		this.datetime=datetime;
		this.requestId=requestId;
		this.seen=1;
		this.type=type;
	}


	public Reply(Reply reply) {
		Date now = new Date();
		
		
		this.sender = reply.sender;
		this.receiver = reply.receiver;
		this.senderRole=reply.senderRole;
		this.content=reply.content;
		this.status=reply.status;
		this.datetime=now;
		this.requestId=reply.requestId;
		this.seen=0;
		this.type=reply.type;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.util.Date getDatetime() {
		return datetime;
	}

	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getSenderRole() {
		return senderRole;
	}

	public void setSenderRole(int senderRole) {
		this.senderRole = senderRole;
	}

	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
	
}
