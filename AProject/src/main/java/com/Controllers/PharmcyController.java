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
public class PharmcyController {
	
	@Autowired
	private PharmacyService pharmcyservice;
	
	@Autowired
	private PrescriptionService prescriptionService;
	
	@Autowired
	private PatientService patientservice;
	

	@Autowired
	private AdvService advService;
	

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private LoginService loginService;
	
	
	
	@RequestMapping("/pharmacyindex/{pharmId}")
	public String pharmacyindex(@PathVariable int pharmId,ModelMap modelmap){
		
		List<Prescription> presc1 = new ArrayList<>();
		
		prescriptionService.getPrescByPharmacy(pharmId, 0)
		.forEach(presc1::add);
		
		List<DataRequest> dataRequest1 = new ArrayList<>();
		
		for(Prescription p1 : presc1){
			dataRequest1.add(new DataRequest(p1,patientservice.getOnePatient(p1.getPatientId()).getName()));
		}
		
		
		
		
		List<Prescription> presc2 = new ArrayList<>();
		
		prescriptionService.getPrescByPharmacy(pharmId, 1)
		.forEach(presc2::add);
		
		List<DataRequest> dataRequest2 = new ArrayList<>();
		
		for(Prescription p2 : presc2){
			dataRequest2.add(new DataRequest(p2,patientservice.getOnePatient(p2.getPatientId()).getName()));
		}
		

		modelmap.put("newp",dataRequest1);
		modelmap.put("old",dataRequest2);
		return "Pharmacy/noti";
	}

	@RequestMapping("/pharmacysearch")
	public String pharmacysearch(){
		return "Pharmacy/home";
	}
	
	@RequestMapping("/pharmacyadv/{id}")
	public String pharmacyadv(@PathVariable int id,ModelMap modelmap){
		
		
		modelmap.put("ads",advService.getAdvBySender(id,3));
		return "pharmacy/adv";
	}
	
	@RequestMapping("/pharmacyprofile")
	public String pharmacyprofile(){
		return "Pharmacy/profile";
	}
	
	/*@RequestMapping("/users")
	public String sayHi(ModelMap modelmap){
		modelmap.put("users",pharmcyservice.getAllPharmacies());
		
		return "User/clinic";
	}*/
	
	@RequestMapping(method=RequestMethod.POST, value="/addpharmacy")
	public String addPharmacy(@RequestBody @ModelAttribute Pharmacy pharmacy,ModelMap modelmap){
		
		if(pharmcyservice.pharmacyByEmail(pharmacy.getEmail())==null && loginService.loginByEmail(pharmacy.getEmail())==null){
			
			int code=notificationService.sendNotification(pharmacy.getEmail());
			pharmcyservice.addPharmacy(pharmacy);
			
			modelmap.put("coded",code);
			modelmap.put("role",pharmacy.getRole());
			modelmap.put("id",pharmacy.getPharmacyId());
			
			return "register/ver";
		}
		
		else{
			modelmap.put("error","This email is already used");
			return "register/pharmacy";
		}
		
	}
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/prescription/{id}")
	public String prescription(@PathVariable int id,ModelMap modelmap){
		modelmap.put("pharmacy",pharmcyservice.getOnePharmacy(id));
		return "Patient/profphar";
	}

}
