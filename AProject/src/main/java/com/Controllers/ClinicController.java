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
public class ClinicController {
	
	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private DoctorService doctorservice;
	
	@Autowired
	private PharmacyService pharmacyservice;
	
	@Autowired
	private LabService labService;
	
	@Autowired
	private ConsultService consultService;

	@Autowired
	private RequestService requestservice;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private PatientService patientservice;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private AdvService advService;
	
	@RequestMapping("/clinicindex/{recId}")
	public String clinicnotification(@PathVariable int recId,ModelMap modelmap){
		
		List<Request> requests = new ArrayList<>();
		
		requestservice.requestByReceiver(recId, 5)
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
		return "Clinic/noti";
	}
	
	@RequestMapping("/clinicsearch")
	public String clinicnotification(ModelMap modelmap){
		return "Clinic/home";
	}
	
	@RequestMapping("/clinicprofile/{id}/{role}")
	public String clinicprofile(@PathVariable int id,@PathVariable int role,ModelMap modelmap){
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
				dataRequest.add(new DataRequest(r,labService.getOneLab(r.getSender()).getName()));
			}
		}
		
		else if(role==4){
			for(Reply r : replies){
				dataRequest.add(new DataRequest(r,doctorservice.getOneDoctor(r.getSender()).getName()));
			}
		}

		modelmap.put("replies",dataRequest);
		
		return "Clinic/profile";
	}
	
	
	@RequestMapping("/clinicadv/{id}")
	public String clinicadvertistment(@PathVariable int id,ModelMap modelmap){
		
		modelmap.put("ads",advService.getAdvBySender(id,5));
		return "Clinic/adv";
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addconsult/{clinicid}")
	public String addConsult(@RequestBody @ModelAttribute Consult consult,@PathVariable int clinicid){
		
		Consult con=new Consult(consult.getConName(),consult.getDay(),consult.getTime(),consult.getDoctor(),consult.getCost(),clinicid);
		consultService.addConsult(con);
		return "redirect:/clinicconsults/{clinicid}";
	}
	
	//Delete a consult by consult ID
	@RequestMapping(method=RequestMethod.GET, value="/deleteconsult/{clinicid}/{consultid}")
	public String deleteConsult(@RequestBody @PathVariable int clinicid,@PathVariable int consultid){
		consultService.deleteConsult(consultid);
		return "redirect:/clinicconsults/{clinicid}";
	}
	
	@RequestMapping(value="/clinicconsults/{clinicid}")
	public String getConsults(@PathVariable int clinicid,ModelMap modelmap){
		modelmap.put("consults",consultService.getConsults(clinicid));
		
		return "Clinic/addconsults";
	}
	
	@RequestMapping("/clinicconsults")
	public String clinicconsults(ModelMap modelmap){
		return "Clinic/addconsults";
	}
	/*@RequestMapping("/clinics")
	public String sayHi(ModelMap modelmap){
		modelmap.put("clinics",clinicService.getAllClinics());
		
		return "Clinic/profile";
	}*/
	
	@RequestMapping(method=RequestMethod.POST, value="/addclinic")
	public String addClinic(@RequestBody @ModelAttribute Clinic clinic,ModelMap modelmap){
		
		if(clinicService.clinicByEmail(clinic.getEmail())==null && loginService.loginByEmail(clinic.getEmail())==null){
			
			int code=notificationService.sendNotification(clinic.getEmail());
			clinicService.addClinic(clinic);
			
			modelmap.put("coded",code);
			modelmap.put("role",clinic.getRole());
			modelmap.put("id",clinic.getClinicId());
			
			return "register/ver";
		}
		else{
			modelmap.put("error","This email is already used");
			return "register/clinic";
		}
		
	}

	
	@RequestMapping(value="clinic/{id}")
	public String getOneClinic(@PathVariable int id,ModelMap modelmap){
		modelmap.put("clinic",clinicService.getOneClinic(id));
		return "Patient/profcli";
	}
	
	@RequestMapping(value="consults/{id}")
	public String getConsult(@PathVariable int id,ModelMap modelmap){
		modelmap.put("consults",consultService.getConsults(id));
		return "Patient/consultlist";
	}
	
	
	/*@RequestMapping(value="/deleterequestclinic/{requestid}/{receiverId}")
	public String deleteRequestclinic(@PathVariable int requestid,@PathVariable int receiverId){
		
		requestservice.deleteOneRequest(requestid);
		return "redirect:clinicindex/{receiverId}";
	}*/

	
}
