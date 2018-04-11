package com.models;

public class Code {
	
	private int encode;
	private int code;


	public Code(){
		
	}
	
	public Code(int encode,int code){
		this.encode=encode;
		this.code=code;
		
		
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getEncode() {
		return encode;
	}

	public void setEncode(int encode) {
		this.encode = encode;
	}


	
	

}
