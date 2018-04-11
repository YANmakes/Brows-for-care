package com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.models.*;
import com.services.*;

@Controller
public class RequestController {
	
	@Autowired
	private RequestService requestservice;

	//Adding a request by the patient for all CLINICS,LABS and DOCTORS 
			@RequestMapping(value="request/{receiverid}/{userid}/{role}/{content}")
			public String addRequest(@PathVariable int receiverid,@PathVariable int userid,@PathVariable String role,@PathVariable String content,ModelMap modelmap){
				
				if(role.equals("clinic")){
					Request request=new Request(userid,receiverid,5,content);
					requestservice.addRequest(request);
				}
				else if(role.equals("lab")){
					Request request=new Request(userid,receiverid,6,content);
					requestservice.addRequest(request);
				}
				else if(role.equals("doctor")){
					Request request=new Request(userid,receiverid,4,content);
					requestservice.addRequest(request);
				}
				
				return "redirect:/patientprofile/{userid}";
			}
			
			
			
	@RequestMapping(value="deleterequest/{requestid}/{id}/{role}")
	public String deleteRequest(@PathVariable int requestid,@PathVariable int id,@PathVariable int role){
		
		requestservice.deleteOneRequest(requestid);
		
		if(role==2)
			return "redirect:/patientprofile/{id}";
			
		else if(role==4)
			return "redirect:/doctornotification/{id}";
		else if(role==5)
			return "redirect:/clinicindex/{id}";
		else
			return "redirect:/labnotification/{id}";
		
	}
	
	@RequestMapping(value="/seenrequest/{requestId}/{receiverId}/{role}")
	public String sean(@PathVariable int requestId,@PathVariable int receiverId,@PathVariable int role,ModelMap modelmap){
		
		Request request=requestservice.getOneRequest(requestId);
		//int id=reply.getReceiver();
		//Reply newReply=new Reply(reply.getReplyId(),reply.getSender(),reply.getReceiver(),reply.getSenderRole(),
				//reply.getStatus(),reply.getDatetime(),reply.getContent(),reply.getRequestId());
		
		request.setSeen(1);
		requestservice.addRequest(request);
		if(role==5)
			return "redirect:/clinicindex/{receiverId}";
		else if(role==6)
			return "redirect:/labnotification/{receiverId}";
		else if(role==4)
			return "redirect:/doctornotification/{receiverId}";
		else
			return "";
	}
	
	
	
	
}
