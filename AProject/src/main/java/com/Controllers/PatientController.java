package com.Controllers;


import java.util.ArrayList;
import java.util.List;


import javax.mail.Session;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.models.*;
import com.services.*;



@Controller
@Scope("session")
public class PatientController {
	
	@Autowired
	private PatientService patientservice;
	
	@Autowired
	private DoctorService doctorservice;
	
	@Autowired
	private PharmacyService pharmacyservice;
	
	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private LabService labService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private RequestService requestservice;
	
	@Autowired
	private PrescriptionService prescriptionService;
	
	
	
	
	//Get all the doctors by patient using doctor service 
	@RequestMapping( value="/patientindex")
	public String userindex(ModelMap modelmap){
		modelmap.put("doctors",doctorservice.getValidDoctors());
		return "patient/doctor";
	}
	
	
	//Get all the pharmacies by patient using pharmacy service
		@RequestMapping("/patientpharmacy")
		public String patientpharmacy(ModelMap modelmap){
			modelmap.put("pharmacies",pharmacyservice.getValidPharmacy());
			return "Patient/pharmacy";
		}
	
		
		//Get all the clinics by patient using clinic service
		@RequestMapping("/patientclinic")
		public String patientclinic(ModelMap modelmap){
			
			modelmap.put("clinics",clinicService.getValidClinics());
			return "Patient/clinic";
		}
		
		
		//Get all the labs by patient using lab service
		@RequestMapping("/patientlab")
		public String patientlab(ModelMap modelmap){
			modelmap.put("labs",labService.getValidULabs());
			return "Patient/lab";
		}
		
		
		
		//Get all the prescriptions by patient using prescription service
				@RequestMapping("/prescs/{patientId}")
				public String patientPresc(@PathVariable int patientId,ModelMap modelmap){
					
					List<Prescription> presc1 = new ArrayList<>();
					
					prescriptionService.getPrescByPatient(patientId, 0)
					.forEach(presc1::add);
					
					List<DataRequest> dataRequest1 = new ArrayList<>();
					
					for(Prescription p1 : presc1){
						dataRequest1.add(new DataRequest(p1,pharmacyservice.getOnePharmacy(p1.getPharmId()).getName()));
					}
					
					
					
					
					List<Prescription> presc2 = new ArrayList<>();
					
					prescriptionService.getPrescByPatient(patientId, 1)
					.forEach(presc2::add);
					
					List<DataRequest> dataRequest2 = new ArrayList<>();
					
					for(Prescription p2 : presc2){
						dataRequest2.add(new DataRequest(p2,pharmacyservice.getOnePharmacy(p2.getPharmId()).getName()));
					}
					

					modelmap.put("newp",dataRequest1);
					modelmap.put("old",dataRequest2);
					return "Patient/presc";
					
					
				}
				
				
		//Get the all Requests which are made by the patient by senderid and display in the patient profile page.
		
		@RequestMapping("/patientprofile/{senderId}")
		public String userprofile(@PathVariable int senderId,ModelMap modelmap){
			
			List<Request> request = new ArrayList<>();
			
			requestService.requestBySender(senderId)
			.forEach(request::add);
			
			List<DataRequest> dataRequest = new ArrayList<>();
			for(Request r : request){
				if(r.getReceiverRole()==5)
					dataRequest.add(new DataRequest(r,clinicService.getOneClinic(r.getReceiver()).getName()));
				
				else if(r.getReceiverRole()==6)
					dataRequest.add(new DataRequest(r,labService.getOneLab(r.getReceiver()).getName()));
				
				else if(r.getReceiverRole()==4);
					//dataRequest.add(new DataRequest(r,doctorservice.getOneDoctor(r.getReceiver()).getName()));
			}
	
			
			modelmap.put("requests",dataRequest);
			
			return "Patient/profile";
		}
		
		//Get the all Replies which arrive to the a particular patient by ReceiverID and display in the patient 
		// notification page
		
		@RequestMapping("/patientnotification/{receiverId}")
		public String patientnotification(@PathVariable int receiverId,ModelMap modelmap){
			
			List<Reply> reply = new ArrayList<>();
			
			replyService.replyByReceiver(receiverId)
			.forEach(reply::add);
			
			

			List<DataRequest> dataRequest = new ArrayList<>();
			List<DataRequest> dataRequest2 = new ArrayList<>();
			for(Reply r : reply){
				
				if(r.getSeen()==0){
					
					if(r.getSenderRole()==5)
						dataRequest.add(new DataRequest(r,clinicService.getOneClinic(r.getSender()).getName()));
					
					else if(r.getSenderRole()==6)
						dataRequest.add(new DataRequest(r,labService.getOneLab(r.getSender()).getName()));
					
					else if(r.getSenderRole()==4)
						dataRequest.add(new DataRequest(r,doctorservice.getOneDoctor(r.getSender()).getName()));
				}
				
				else if(r.getSeen()==1){
					
					if(r.getSenderRole()==5)
						dataRequest2.add(new DataRequest(r,clinicService.getOneClinic(r.getSender()).getName()));
					
					else if(r.getSenderRole()==6)
						dataRequest2.add(new DataRequest(r,labService.getOneLab(r.getSender()).getName()));
					
					else if(r.getSenderRole()==4)
						dataRequest2.add(new DataRequest(r,doctorservice.getOneDoctor(r.getSender()).getName()));
				}
				
			}
	
			if(dataRequest != null)
				modelmap.put("newreplys",dataRequest);
			else
				modelmap.put("error","No new notifications....!");
			
			
			if(dataRequest2 != null)
				modelmap.put("oldreplys",dataRequest2);
			else
				modelmap.put("error","No notifications....!");
			return "Patient/noti";
		}
		
		
		@RequestMapping(method=RequestMethod.POST, value="/addpatient")
		public String addPatient(@RequestBody @ModelAttribute Patient patient,ModelMap modelmap){
			
			if(patientservice.patientByEmail(patient.getEmail())==null && loginService.loginByEmail(patient.getEmail())==null){
				
				int code=notificationService.sendNotification(patient.getEmail());

					patientservice.addPatient(patient);
					
					modelmap.put("coded",code);
					modelmap.put("role",patient.getRole());
					modelmap.put("id",patient.getPatientId());
					return "register/ver";
			
				
			}
			
			else{
				modelmap.put("error","This email is already used");
				return "register/patient";
			}
			
			
		}
		
		 
		@RequestMapping( value="/editpatient/{patientid}")
		public String editpatient(@PathVariable int patientid,ModelMap modelmap){
			
			modelmap.put("patient",patientservice.getOnePatient(patientid));
			
			return "Patient/patientedit";
		}
		
		@RequestMapping( value="/savepatient")
		public String savepatient(@RequestBody @ModelAttribute Patient patient,ModelMap modelmap,HttpSession session){
			
			Login login=new Login(patient.getName(),patient.getPassword(),patient.getEmail(),patient.getRole(),patient.getActive());
			loginService.addLogin(login);
			
			patientservice.addPatient(patient);
			
			session.removeAttribute("name");
			session.setAttribute("name", patient.getName());
			
			return "redirect:/patientindex";
		}
		
		
		
		
	
		
		@RequestMapping(value="seen/{replyId}/{receiverId}")
		public String sean(@PathVariable int replyId,@PathVariable int receiverId,ModelMap modelmap){
			
			Reply reply=replyService.getOneReply(replyId);
			//int id=reply.getReceiver();
			Reply newReply=new Reply(reply.getReplyId(),reply.getSender(),reply.getReceiver(),reply.getSenderRole(),
					reply.getStatus(),reply.getDatetime(),reply.getContent(),reply.getRequestId());
			
			replyService.addReply(newReply);
			
			return "redirect:/patientnotification/{receiverId}";
		}
		
		
		
		
		
		@RequestMapping( value="/unblock/{id}/{role}")
		public String unblock(@PathVariable int id,@PathVariable int role,ModelMap modelmap){
			
			if(role==2){
				Patient patient=patientservice.getOnePatient(id);
				patient.setActive(1);
				Login login=loginService.getOneLogin(patient.getName());
				login.setActive(1);
				
				loginService.addLogin(login);
				patientservice.addPatient(patient);
				
				return "redirect:/adminpatient";
			}
			
			else if(role==4){
				Doctor doctor=doctorservice.getOneDoctor(id);
				doctor.setActive(1);
				Login login=loginService.getOneLogin(doctor.getName());
				login.setActive(1);
				
				loginService.addLogin(login);
				doctorservice.addDoctor(doctor);
				
				return "redirect:/admindoctor";
			}
			
			
			else if(role==3){
				Pharmacy pharmacy=pharmacyservice.getOnePharmacy(id);
				pharmacy.setActive(1);
				Login login=loginService.getOneLogin(pharmacy.getName());
				login.setActive(1);
				
				loginService.addLogin(login);
				pharmacyservice.addPharmacy(pharmacy);
				
				return "redirect:/adminpharmcy";
			}
			
			
			else if(role==5){
				Clinic clinic=clinicService.getOneClinic(id);
				clinic.setActive(1);
				Login login=loginService.getOneLogin(clinic.getName());
				login.setActive(1);
				
				loginService.addLogin(login);
				clinicService.addClinic(clinic);;
				
				return "redirect:/adminclinic";
			}
			
			else{
				Lab lab=labService.getOneLab(id);
				lab.setActive(1);
				Login login=loginService.getOneLogin(lab.getName());
				login.setActive(1);
				
				loginService.addLogin(login);
				labService.addLab(lab);
				
				return "redirect:/adminlab";
			}
			
		}
	
}
