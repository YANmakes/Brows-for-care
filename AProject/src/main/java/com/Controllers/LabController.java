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
public class LabController {
	
	@Autowired
	private LabService labservice;

	@Autowired
	private TestService testservice;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private PatientService patientservice;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private AdvService advService;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private LoginService loginService;

	
	
	//go to home page
	@RequestMapping("/labindex")
	public String labindex(ModelMap modelmap){
		modelmap.put("labs",labservice.getAllULabs());
		
		return "Lab/home";
	}
	
	
	//go to notification page
	@RequestMapping("/labnotification/{receiverId}")
	public String labnotification(@PathVariable int receiverId,ModelMap modelmap){
		
		List<Request> requests = new ArrayList<>();
		
		requestService.requestByReceiver(receiverId, 6)
		.forEach(requests::add);
		
		

		List<DataRequest> dataRequest = new ArrayList<>();
		List<DataRequest> dataRequest2 = new ArrayList<>();
		
		for(Request r : requests){
			
			if(r.getSeen()==0){
					dataRequest.add(new DataRequest(r,patientservice.getOnePatient(r.getSender()).getName()));
				
			}
			
			else if(r.getSeen()==1){
					dataRequest2.add(new DataRequest(r,patientservice.getOnePatient(r.getSender()).getName()));
			}
			
		}

		if(dataRequest != null)
			modelmap.put("newrequests",dataRequest);
		else
			modelmap.put("error","No new notifications....!");
		
		
		if(dataRequest2 != null)
			modelmap.put("oldrequests",dataRequest2);
		else
			modelmap.put("error","No notifications....!");
		
			return "Lab/noti";
	}
	
	
	
	
	
	//to go to history page
	@RequestMapping("/labprofile/{id}/{role}")
	public String labprofile(@PathVariable int id,@PathVariable int role,ModelMap modelmap){
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
		
		return "Lab/profile";
	}
	
	
	//go to advertisement page
	@RequestMapping("/labadv/{id}")
	public String labadv(@PathVariable int id,ModelMap modelmap){
		
		modelmap.put("ads",advService.getAdvBySender(id,6));
		return "Lab/adv";
	}
	
	
	//test adding page
	@RequestMapping("/labtest/{labid}")
	public String labtest(@PathVariable int labid,ModelMap modelmap){
		modelmap.put("tests",testservice.getTests(labid));
		
		return "Lab/addtest";
	}
/*	@RequestMapping("/users")
	public String sayHi(ModelMap modelmap){
		modelmap.put("users",labservice.getAllLabs());
		
		return "User/clinic";
	}*/
	
	
	//add new lab
	@RequestMapping(method=RequestMethod.POST, value="/addlab")
	public String addLab(@RequestBody @ModelAttribute Lab lab,ModelMap modelmap){
		
		if(labservice.labByEmail(lab.getEmail())==null && loginService.loginByEmail(lab.getEmail())==null){
			
			int code=notificationService.sendNotification(lab.getEmail());
			labservice.addLab(lab);
			
			modelmap.put("coded",code);
			modelmap.put("role",lab.getRole());
			modelmap.put("id",lab.getLabId());
			
			return "register/ver";
		}
		else{
			modelmap.put("error","This email is already used");
			return "register/lab";
		}
		
	}
	
	
	//get one lab detail
	@RequestMapping(value="/lab/{id}")
	public String getOneLab(@PathVariable int id,ModelMap modelmap){
		modelmap.put("lab",labservice.getOneLab(id));
		return "Patient/proflab";
	}

	
	
	//get one lab's test list
	@RequestMapping(value="/tests/{labid}")
	public String getTests(@PathVariable int labid,ModelMap modelmap){
		modelmap.put("tests",testservice.getTests(labid));
		
		return "Patient/proflablist";
	}
	
	
	//add new test 
	@RequestMapping(method=RequestMethod.POST, value="/addtest/{labid}")
	public String addTest(@RequestBody @ModelAttribute Test test,@PathVariable int labid){
		
		Test tests = new Test(test.getTestName(),test.getTime(),test.getDay(),test.getCost(),labid);
		testservice.addTest(tests);
		return "redirect:/labtest/{labid}";
	}
	
	
	
	
	//delete a test
	@RequestMapping(method=RequestMethod.GET, value="/deleteTest/{testid}/{labid}")
	public String deleteTest(@RequestBody @PathVariable int testid,@PathVariable int labid){
		testservice.deleteTest(testid);
		return "redirect:/labtest/{labid}";
	}
	
	
	
	
	//Get all the reports by both the patient and lab 
	@RequestMapping(method=RequestMethod.GET, value="/reports/{id}/{role}")
	public String reports(@PathVariable int id,@PathVariable int role,ModelMap modelmap){
		
		List<Report> report = new ArrayList<>();

		
		if(role==2){
			reportService.getReportByPatient(id)
			.forEach(report::add);
			
			List<DataRequest> dataRequest = new ArrayList<>();
			
			for(Report r : report){
				dataRequest.add(new DataRequest(r,labservice.getOneLab(r.getLabId()).getName()));
			}
			
			modelmap.put("reports",dataRequest);
			
			return "Patient/report";
		}
		
		if(role==6){
			
			reportService.getReportByLab(id)
			.forEach(report::add);
			
			List<DataRequest> dataRequest = new ArrayList<>();
			
			for(Report r : report){
				dataRequest.add(new DataRequest(r,patientservice.getOnePatient(r.getPatientId()).getName()));
			}
			
			modelmap.put("reports",dataRequest);
			
			return "Lab/report";
		}
		else
			return "";
			
		
	}
	
}
