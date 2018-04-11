package com.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.models.*;
import com.services.*;

@Controller
public class AdvController {
	
	@Autowired
	private AdvService advService;
	
	 //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E:/upload/ads/";
    
    
    @PostMapping("/uploadadv/{id}/{role}") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes
                                   ,@PathVariable int id,@PathVariable int role) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            
           Adv adv =new Adv(id,role,file.getOriginalFilename(),UPLOADED_FOLDER);
           advService.addAdv(adv);
            
            
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(role==3)
        	return "redirect:/pharmacyadv/{id}";
        else if(role==4)
        	return "redirect:/doctoradv/{id}";
        else if(role==5)
        	return "redirect:/clinicadv/{id}";
        else
        	return "redirect:/labadv/{id}";
    }
    
    
  //Method for viewing a ad
    @RequestMapping(value = "/downadv/{id}", method = RequestMethod.GET)
    public void getAdv(HttpServletResponse response,@PathVariable int id) {
        try {
          // get your file as InputStream
        	Adv adv = advService.getOneAdv(id);
        	
        	File file = new File(adv.getLocation()+adv.getAdName());
        	InputStream is = new FileInputStream(file);
        	
        	
          // copy it to response's OutputStream
        	org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
          response.flushBuffer();
        } catch (IOException ex) {
         // log.info("Error writing file to output stream. Filename was '{}'", "a", ex);
          throw new RuntimeException("IOError writing file to output stream");
        }

    }
    
    //Method for deleting a ad
    @RequestMapping(value="/deleteadv/{adId}/{id}/{role}")
  	public String deleteAdv(@PathVariable int adId,@PathVariable int id,@PathVariable int role){
  		
    	advService.deleteAdv(adId);
    	
    	
    	if(role==3)
    		return "redirect:/pharmacyadv/{id}";
      	else if(role==4)
          	return "redirect:/doctoradv/{id}";
      	else if(role==5)
          	return "redirect:/clinicadv/{id}";
      	else if(role==6)
          	return "redirect:/labadv/{id}";
      	else
      		return "";
      }
    
    @RequestMapping(value="/admindeleteadv/{adId}/{type}")
  	public String adminDeleteAdv(@PathVariable int adId,@PathVariable int type){
  		
    	advService.deleteAdv(adId);
    	
    	if(type==0)
    		return "redirect:/adminadv";
    	else if(type==1)
    		return "redirect:/admineditadv";
    	else
    		return "";
      	
      }
    
    @RequestMapping(value="/blockadv/{adId}/{type}")
  	public String blockadv(@PathVariable int adId,@PathVariable int type){
  		
    	Adv adv=advService.getOneAdv(adId);
    	
    	adv.setActive(type);
    	
    	advService.addAdv(adv);
      	
      	return "redirect:/adminadv";
      	
      }
    
    @RequestMapping(value="/acceptance/{adId}/{type}")
  	public String acceptance(@PathVariable int adId,@PathVariable int type){
  		
    	Adv adv=advService.getOneAdv(adId);
    	
    	adv.setActive(type);
    	
    	advService.addAdv(adv);
      	
      	return "redirect:/admineditadv";
      	
      }
    
    
    //Get all ads for showing the gallery in user pages
    @RequestMapping(value="/getads/{id}/{role}")
  	public String getAllAdv(@PathVariable int id,@PathVariable int role,ModelMap modelmap){
  		
    	
    	
    	if(role==2){
    		modelmap.put("ads",advService.getAdvs());
    		return "Patient/advertisments";
    	}
    		
    	else if(role==3){
    		modelmap.put("ads",advService.getAdvs());
    		return "Pharmacy/home";
    	}
      	else if(role==4){
      		modelmap.put("ads",advService.getAdvs());
      		return "Doctor/home";
      	}
      	else if(role==5){
      		modelmap.put("ads",advService.getAdvs());
    		return "Clinic/home";
      	}
      	else if(role==6){
      		modelmap.put("ads",advService.getAdvs());
    		return "Lab/home";
      	}
      	else
      		return "";
      }
    
    
  
   		
    	
    		
    	

}
