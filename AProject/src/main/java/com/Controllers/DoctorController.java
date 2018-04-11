package com.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.models.*;
import com.services.*;



@Controller
public class DoctorController {
	
	
	//to connect the services to the variables
	@Autowired
	private PatientService patientservice;
	
	@Autowired
	private DoctorService doctorservice;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private LabService labService;
	
	@Autowired
	private AdvService advService;
	
	@Autowired
	private LoginService loginService;
	
	
	
	
	//go to homepage
	@RequestMapping("/doctorindex")
	public String doctorindex(){
		//modelmap.put("users",userservice.getAllUsers());
		return "Doctor/home";
	}
	
	
	//go to notification page
	@RequestMapping("/doctornotification/{doctorid}")
	public String doctornotification(@PathVariable int doctorid,ModelMap modelmap){
		
		List<Request> request = new ArrayList<>();
		
		requestService.requestByReceiver(doctorid,4)
		.forEach(request::add);
		
		List<DataRequest> dataRequest1 = new ArrayList<>();
		List<DataRequest> dataRequest2 = new ArrayList<>();
		
		for(Request r : request){
			
			if(r.getSeen()==0){
				DataRequest dr=new DataRequest();
				dr.setUsrName(patientservice.getOnePatient(r.getSender()).getName());
				dr.setRequest(r);
				
				dataRequest1.add(dr);
			}
					//dataRequest1.add(new DataRequest(r,patientservice.getOnePatient(r.getSender()).getName()));
			
			else if(r.getSeen()==1)
				    dataRequest2.add(new DataRequest(r,patientservice.getOnePatient(r.getSender()).getName()));
		}
		
		if(dataRequest1 != null)
			modelmap.put("newrequests",dataRequest1);
		else
			modelmap.put("error","No new notifications....!");
		
		
		if(dataRequest2 != null)
			modelmap.put("oldrequests",dataRequest2);
		else
			modelmap.put("error","No notifications....!");

		return "Doctor/noti";
	}
	
	
	
	//go to profile
	@RequestMapping("/doctorprofile/{id}/{role}")
	public String userprofile(@PathVariable int id,@PathVariable int role,ModelMap modelmap){
		
		List<Reply> replies = new ArrayList<>();
		
		replyService.replyBySender(id, role)
		.forEach(replies::add);
	
		List<DataRequest> dataRequest = new ArrayList<>();
		
		if(role==5){
			for(Reply r : replies){
				dataRequest.add(new DataRequest(r,patientservice.getOnePatient(r.getReceiver()).getName()));
			}
		}
		else if(role==6){
			for(Reply r : replies){
				dataRequest.add(new DataRequest(r,patientservice.getOnePatient(r.getReceiver()).getName()));
			}
		}
		else if(role==4){
			for(Reply r : replies){
				dataRequest.add(new DataRequest(r,patientservice.getOnePatient(r.getReceiver()).getName()));
			}
		}

		modelmap.put("replies",dataRequest);
		
		return "Doctor/profile";
	}
	
	
	
	//go to advertisement page
	@RequestMapping("/doctoradv/{id}")
	public String doctoradv(@PathVariable int id,ModelMap modelmap){
		
		modelmap.put("ads",advService.getAdvBySender(id,4));

		return "Doctor/adv";
	}
	
	
	//get all doctors
	@RequestMapping("/alldoctors")
	public String alldoctors(ModelMap modelmap){
		List<Doctor> doctors = new ArrayList<>();
				doctorservice.getAllDoctors()
				.forEach(doctors::add);
				
		modelmap.put("doctors",doctors);
		return "User/doctor";
	}
	
	
	
	//to add new doctor
	@RequestMapping(method=RequestMethod.POST, value="/adddoctor")
	public String addDoctor(@RequestBody @ModelAttribute Doctor doctor,ModelMap modelmap){
		
		//email validation
		if(doctorservice.doctorByEmail(doctor.getEmail())==null && loginService.loginByEmail(doctor.getEmail())==null){
			
			int code=notificationService.sendNotification(doctor.getEmail());
			doctorservice.addDoctor(doctor);
			
			modelmap.put("coded",code);
			modelmap.put("role",doctor.getRole());
			modelmap.put("id",doctor.getDoctorId());
			
			return "register/ver";
		}
		
		
		else{
			modelmap.put("error","This email is already used");
			return "register/patient";
		}
	}

	
	
	
	//to get a doctor
	@RequestMapping(value="/doctor/{id}")
	public String getOneDoctor(@PathVariable int id,ModelMap modelmap){
		modelmap.put("doctor",doctorservice.getOneDoctor(id));
		return "User/pharmacydetail";
	}
	
	
	
	//press seen button
	@RequestMapping(value="/seendoctorrequest/{requestId}/{receiverId}")
	public String seen(@PathVariable int requestId,@PathVariable int receiverId,ModelMap modelmap){
		
		Request request=requestService.getOneRequest(requestId);
		//int id=reply.getReceiver();
		//Reply newReply=new Reply(reply.getReplyId(),reply.getSender(),reply.getReceiver(),reply.getSenderRole(),
				//reply.getStatus(),reply.getDatetime(),reply.getContent(),reply.getRequestId());
		
		request.setSeen(1);
		requestService.addRequest(request);
		
		return "redirect:doctornotification/{receiverId}";
	}
	
	
	
	//delete a request by the x
	@RequestMapping(value="/deleterequestdoctor/{requestid}/{receiverId}")
	public String deleteRequestdoctor(@PathVariable int requestid,@PathVariable int receiverId){
		
		requestService.deleteOneRequest(requestid);
		return "redirect:doctornotification/{receiverId}";
	}

}
