package com.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.models.*;
import com.services.*;


@Controller
public class LoginController {
	
	@Autowired
	LoginService loginservice;
	
	@Autowired
	PatientService patientservice;
	
	@Autowired
	LabService labservice;
	
	@Autowired
	ClinicService clinicservice;
	
	@Autowired
	DoctorService doctorservice;
	
	@Autowired
	PharmacyService pharmacyservice;
	
	@Autowired
	AdminService adminservice;
	
	@GetMapping("/login")
	public String login(Model model){
		return "Login/log";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/validation")
	public String validation(@RequestBody @ModelAttribute Login login,ModelMap modelmap,HttpSession session){
		
		//Login user= loginservice.getValidation(login.getUserName(),login.getPassword());
		
		Login user= loginservice.getValidation(login.getEmail(),login.getPassword());
		
		if(user!=null){
			
			if(user.getRole()==1){
				session.setAttribute("name", user.getUserName());
				//session.setAttribute("id", adminservice.adminByName(user.getUserName()).getAdminId());

				return "redirect:/adminpatient";
			}
			else if(user.getRole()==2){
				session.setAttribute("name", user.getUserName());
				session.setAttribute("id", patientservice.patientByName(user.getUserName()).getPatientId());
				
				return "redirect:/patientindex";
			}
			else if(user.getRole()==3){
				session.setAttribute("name", user.getUserName());
				session.setAttribute("id", pharmacyservice.pharmacyByName(user.getUserName()).getPharmacyId());
				return "redirect:/pharmacysearch";
			}
			else if(user.getRole()==4){
				session.setAttribute("name", user.getUserName());
				session.setAttribute("id", doctorservice.doctorByName(user.getUserName()).getDoctorId());
				return "redirect:/doctorindex";
			}
			else if(user.getRole()==5){
				session.setAttribute("name", user.getUserName());
				session.setAttribute("id", clinicservice.clinicByName(user.getUserName()).getClinicId());
				return "redirect:/clinicsearch";
				
			}
			else if(user.getRole()==6){
				
				session.setAttribute("name", user.getUserName());
				session.setAttribute("id", labservice.labByName(user.getUserName()).getLabId());
				return "redirect:/labindex";
			}
			else{
				modelmap.put("error","User Name or password is incorrect....!");
				return "redirect:/login";
			}
			
		}
		
		else{
			modelmap.put("error","User Name or password is incorrect....!");
			return "Login/log";
		}
		
		
	}
	
		
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		
		session.removeAttribute("name");
		session.removeAttribute("id");
		
		return "redirect:/login";
	}
	
	/*//for security configuration
	@RequestMapping(method=RequestMethod.GET, value="/val/{username}")
	public String validation(@PathVariable String userName,ModelMap modelmap,HttpSession session){
		
		Login user= loginservice.getOneLogin(userName);
		
		if(user!=null){
			
			if(user.getRole()==1){
				return "";
			}
			else if(user.getRole()==2){
				session.setAttribute("name", user.getUserName());
				session.setAttribute("id", patientservice.patientByName(user.getUserName()).getPatientId());
				
				return "redirect:/patientindex";
			}
			else if(user.getRole()==3){
				return "Doctor/home";
			}
			else if(user.getRole()==5){
				session.setAttribute("name", user.getUserName());
				session.setAttribute("id", clinicservice.clinicByName(user.getUserName()).getClinicId());
				return "Clinic/home";
				
			}
			else if(user.getRole()==6){
				
				session.setAttribute("name", user.getUserName());
				session.setAttribute("id", labservice.labByName(user.getUserName()).getLabId());
				return "Lab/home";
			}
			
			else{
				return "";
			}
		}
		
		else{
			modelmap.put("error","User Name or password is incorrect....!");
			return "Login/login";
		}
		
	}
	*/

}
