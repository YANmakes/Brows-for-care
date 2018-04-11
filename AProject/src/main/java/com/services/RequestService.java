package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.*;
import com.models.*;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestrepo;
	

	public List<Request> requestBySender(int senderId){
		List<Request> requests=new ArrayList<>();
		requestrepo.findBySender(senderId)
		.forEach(requests::add);
		
		return requests;
		
	}
	
	
	public List<Request> requestByReceiver(int receiverId,int role){
		List<Request> requests=new ArrayList<>();
		requestrepo.findByReceiverAndReceiverRole(receiverId, role)
		.forEach(requests::add);
		
		return requests;
		
	}
	
	public void addRequest(Request request){
		requestrepo.save(request);
		
	}

	public void deleteOneRequest(int requestid) {
		
		requestrepo.delete(requestid);
	}
	
	public Request getOneRequest(int requestid) {
		
		return requestrepo.findOne(requestid);
	}
}
