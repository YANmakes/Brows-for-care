package com.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class PrescriptionController {
	
	@Autowired
	private PrescriptionService PrescService;
	
	 //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E:/upload/prescriptions/";
    
    
    
    
    @PostMapping("/upload/{patientId}/{pharmId}") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes
                                   ,@PathVariable int patientId,@PathVariable int pharmId) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            
           Prescription presc =new Prescription(patientId,pharmId,file.getOriginalFilename(),UPLOADED_FOLDER);
           PrescService.addPresc(presc);;
            
            
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/prescs/{patientId}";
    }
    
    
    
    
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "Pharmacy/uploadStatus";
    }
    
    
    
    
    @RequestMapping(value = "/downpresc/{id}", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response,@PathVariable int id) {
        try {
          // get your file as InputStream
        	Prescription presc = PrescService.getOnePresc(id);
        	
        	File file = new File(presc.getLocation()+presc.getFilename());
        	InputStream is = new FileInputStream(file);
        	
        	
          // copy it to response's OutputStream
        	org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
          response.flushBuffer();
        } catch (IOException ex) {
         // log.info("Error writing file to output stream. Filename was '{}'", "a", ex);
          throw new RuntimeException("IOError writing file to output stream");
        }

    }
    
    
    @RequestMapping(value="/deletepresc/{prescId}/{id}/{role}")
	public String deletePresc(@PathVariable int prescId,@PathVariable int id,@PathVariable int role){
		
    	PrescService.deletePresc(prescId);
    	if(role==3)
    	return "redirect:/pharmacyindex/{id}";
    	else if(role==2)
        	return "redirect:/prescs/{id}";
    	else
    		return "";
    }
    
    
    @RequestMapping(value="/prescreply/{prescId}/{pharmId}/{type}")
   	public String prescReply(@PathVariable int prescId,@PathVariable int pharmId,@PathVariable int type){
   		
    	if(type==1){
    		Date now = new Date();
    		
    		Prescription presc=PrescService.getOnePresc(prescId);
    		presc.setPseen(1);
    		presc.setUseen(0);
    		presc.setReply("Available");
    		presc.setArrival(now);
    		PrescService.addPresc(presc);
    		
    		return "redirect:/pharmacyindex/{pharmId}";
    	}
    	
    	else if(type==0){
    		Date now = new Date();
    		
    		Prescription presc=PrescService.getOnePresc(prescId);
    		presc.setPseen(1);
    		presc.setUseen(0);
    		presc.setReply("Not Available");
    		presc.setArrival(now);
    		PrescService.addPresc(presc);
    		
    		return "redirect:/pharmacyindex/{pharmId}";
    		
    	}
       
    	else
    		return "";
       }
    
    @RequestMapping(value="/seenpresc/{prescId}/{patientid}")
	public String deletePresc(@PathVariable int prescId,@PathVariable int patientid){
		
    	Prescription presc=PrescService.getOnePresc(prescId);
    	
    	presc.setUseen(1);
    	
    	PrescService.addPresc(presc);
      	return "redirect:/prescs/{patientid}";
    	
    }

    

}
