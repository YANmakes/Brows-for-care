package com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.models.*;
import com.services.AdminService;
import com.services.ClinicService;
import com.services.DoctorService;
import com.services.LabService;
import com.services.LoginService;
import com.services.NotificationService;
import com.services.PatientService;
import com.services.PharmacyService;
import com.services.ReplyService;
import com.services.RequestService;

@Controller
public class HomeController {
	
	@Autowired
	private AdminService admintservice;
	

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
	
	
	@RequestMapping("/bfc")
	public String home(){
		
		return "register/Home";
	}
	
	@RequestMapping("/brows")
	public String register(){
		
		return "register/first";
	}

	@RequestMapping("/clinic")
	public String clinic(){
		
		return "register/clinic";
	}
	
	@RequestMapping("/patient")
	public String patient(){
		
		return "register/patient";
	}
	
	@RequestMapping("/doctor")
	public String doctor(){
		
		return "register/doctor";
	}
	
	@RequestMapping("/lab")
	public String lab(){
		
		return "register/lab";
	}
	
	@RequestMapping("/pharmacy")
	public String pharmacy(){
		
		return "register/pharmacy";
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/verification/{coded}/{role}/{id}")
	public String verfyCode(@RequestBody @ModelAttribute Code code,@PathVariable int coded,@PathVariable int role,@PathVariable int id,ModelMap modelmap){
	
		if(code.getEncode()==coded){
			
			if(role==2){
				Patient patient=patientservice.getOnePatient(id);
				patient.setActive(1);
				patient.setValid(1);
				Login login=new Login(patient.getName(),patient.getPassword(),patient.getEmail(),patient.getRole());
				loginService.addLogin(login);
				
				return "register/welcome";
			}
			
			else if(role==3){
				Pharmacy pharmacy=pharmacyservice.getOnePharmacy(id);
				pharmacy.setActive(1);
				pharmacy.setValid(1);
				Login login=new Login(pharmacy.getName(),pharmacy.getPassword(),pharmacy.getEmail(),pharmacy.getRole());
				loginService.addLogin(login);
				return "register/welcome";
			}
			
			else if(role==4){
				Doctor doctor=doctorservice.getOneDoctor(id);
				doctor.setActive(1);
				doctor.setValid(1);
				Login login=new Login(doctor.getName(),doctor.getPassword(),doctor.getEmail(),doctor.getRole());
				loginService.addLogin(login);
				
				return "register/welcome";
			}
			
			else if(role==5){
				Clinic clinic=clinicService.getOneClinic(id);
				clinic.setActive(1);
				clinic.setValid(1);
				Login login=new Login(clinic.getName(),clinic.getPassword(),clinic.getEmail(),clinic.getRole());
				loginService.addLogin(login);
				return "register/welcome";
			}
			
			else{
				Lab lab=labService.getOneLab(id);
				lab.setActive(1);
				lab.setValid(1);
				Login login=new Login(lab.getName(),lab.getPassword(),lab.getEmail(),lab.getRole());
				loginService.addLogin(login);
				return "register/welcome";
			}
			
		}
		else {
			modelmap.put("msg","The code your entered is mismatch");
			modelmap.put("coded",coded);
			modelmap.put("role",role);
			modelmap.put("id",id);
			
			return "register/ver";
		}
	}
		
		
		
	
	
	

}
