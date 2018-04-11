package com.models;

public class DataRequest {

	private Request request;
	private String usrName;
	private Reply reply;
	private Prescription presc;
	private Report report;
	
	public DataRequest(){
	}
	
	public DataRequest(Report report, String usrName) {
		
		this.report = report;
		this.usrName = usrName;
	}
	public DataRequest(Prescription presc, String usrName) {
		
		this.presc = presc;
		this.usrName = usrName;
	}
	
	public DataRequest(Request request, String usrName) {
	
		this.request = request;
		this.usrName = usrName;
	}
	
	public DataRequest(Reply reply, String usrName) {
		
		this.reply = reply;
		this.usrName = usrName;
	}


	public Request getRequest() {
		return request;
	}


	public void setRequest(Request request) {
		this.request = request;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public Prescription getPresc() {
		return presc;
	}

	public void setPresc(Prescription presc) {
		this.presc = presc;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	

	
	
	
}
