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
import com.services.*;

@Controller
public class AdminController {
	
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
	
	@Autowired
	private AdvService advService;
	

	@RequestMapping("/adminclinic")
	public String adminclinic(ModelMap modelmap){
		
		modelmap.put("all",clinicService.countAllClinic());
		modelmap.put("blocked",clinicService.countBlockedClinics());
		modelmap.put("active",clinicService.countAllClinic()-clinicService.countBlockedClinics());
		
		modelmap.put("clinics",clinicService.validClinics());
	
		return "Admin/clinic";
	}
	
	@RequestMapping("/admindoctor")
	public String admindoctor(ModelMap modelmap){
		
		modelmap.put("all",doctorservice.countAllDoctors());
		modelmap.put("blocked",doctorservice.countBlockedDoctors());
		modelmap.put("active",doctorservice.countAllDoctors()-doctorservice.countBlockedDoctors());
		
		modelmap.put("doctors",doctorservice.validDoctors());
		
		return "Admin/doctor";
	}
	
	
	@RequestMapping("/adminpatient")
	public String adminpatient(ModelMap modelmap){
		
		modelmap.put("all",patientservice.countAllPatients());
		modelmap.put("blocked",patientservice.countBlockedPatients());
		modelmap.put("active",patientservice.countAllPatients()-patientservice.countBlockedPatients());
		
		
		modelmap.put("activepatient",patientservice.getValidPatients(1));
		
		return "Admin/user";
	}
	
	@RequestMapping("/adminlab")
	public String adminlab(ModelMap modelmap){
		
		modelmap.put("all",labService.countAllLabs());
		modelmap.put("blocked",labService.countBlockedLabs());
		modelmap.put("active",labService.countAllLabs()-labService.countBlockedLabs());
		
		modelmap.put("labs",labService.valiLabs());
		return "Admin/lab";
	}
	
	@RequestMapping("/adminpharmcy")
	public String adminpharmcy(ModelMap modelmap){
		
		modelmap.put("all",pharmacyservice.countAllPharmacies());
		modelmap.put("blocked",pharmacyservice.countBlockedPharmacies());
		modelmap.put("active",pharmacyservice.countAllPharmacies()-pharmacyservice.countBlockedPharmacies());
		
		modelmap.put("pharmacies",pharmacyservice.validPharmacy());
		return "Admin/pharmacy";
	}
	
	//Get new ads for showing the gallery in admin page
	@RequestMapping("/adminadv")
	public String adminadv(ModelMap modelmap){
		
		modelmap.put("newads",advService.getActiveAdvs(0));
		
		return "Admin/ad";
	}
	
	@RequestMapping("/addadmin")
	public String addadmin(ModelMap modelmap){
		
		return "Admin/addadmin";
	}
	
	@RequestMapping("/admineditadv")
	public String admineditadv(ModelMap modelmap){
		
		modelmap.put("cur",advService.getActiveAdvs(1));
		
		return "Admin/editad";
	}
	

	@RequestMapping(method=RequestMethod.POST, value="/newadmin")
	public String newadmin(@RequestBody @ModelAttribute Admin admin,ModelMap modelmap){
		
		Login login=new Login(admin.getName(),admin.getPassword(),admin.getEmail(),1);
		loginService.addLogin(login);
		
		admintservice.addAdmin(admin);
		return "redirect:/addadmin";
	}
	
	@RequestMapping("/admindelete/{id}/{role}")
	public String admindelete(@PathVariable int id,@PathVariable int role,ModelMap modelmap){
		
		if(role==2){
			patientservice.deleteOnePatient(id);
			return "redirect:/adminpatient";
		}
			
		else if(role==3){
			pharmacyservice.deleteOnePharmacy(id);
			return "redirect:/adminpharmcy";
		}
		
		else if(role==4){
			doctorservice.deleteOneDoctor(id);
			return "redirect:/admindoctor";
		}
			
		else if(role==5){
			clinicService.deleteOneClinic(id);
			return "redirect:/adminclinic";
		}
			
		else if(role==6){
			labService.deleteOneLab(id);;
			return "redirect:/adminlab";
		}
		else
		return "";
	}
	
	
	
	
	@RequestMapping( value="/block/{id}/{role}")
	public String block(@PathVariable int id,@PathVariable int role,ModelMap modelmap){
		
		if(role==2){
			Patient patient=patientservice.getOnePatient(id);
			patient.setActive(0);
			Login login=loginService.getOneLogin(patient.getName());
			login.setActive(0);
			
			loginService.addLogin(login);
			patientservice.addPatient(patient);
			
			return "redirect:/adminpatient";
		}
		
		else if(role==4){
			Doctor doctor=doctorservice.getOneDoctor(id);
			doctor.setActive(0);
			Login login=loginService.getOneLogin(doctor.getName());
			login.setActive(0);
			
			loginService.addLogin(login);
			doctorservice.addDoctor(doctor);
			
			return "redirect:/admindoctor";
		}
		
		
		else if(role==3){
			Pharmacy pharmacy=pharmacyservice.getOnePharmacy(id);
			pharmacy.setActive(0);
			Login login=loginService.getOneLogin(pharmacy.getName());
			login.setActive(0);
			
			loginService.addLogin(login);
			pharmacyservice.addPharmacy(pharmacy);
			
			return "redirect:/adminpharmcy";
		}
		
		
		else if(role==5){
			Clinic clinic=clinicService.getOneClinic(id);
			clinic.setActive(0);
			Login login=loginService.getOneLogin(clinic.getName());
			login.setActive(0);
			
			loginService.addLogin(login);
			clinicService.addClinic(clinic);;
			
			return "redirect:/adminclinic";
		}
		
		else{
			Lab lab=labService.getOneLab(id);
			lab.setActive(0);
			Login login=loginService.getOneLogin(lab.getName());
			login.setActive(0);
			
			loginService.addLogin(login);
			labService.addLab(lab);
			
			return "redirect:/adminlab";
		}
		
	}
}
