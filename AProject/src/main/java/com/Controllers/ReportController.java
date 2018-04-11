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
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private PatientService patientservice;
	
	
	
	 //Save the uploaded report to this folder
    private static String UPLOADED_FOLDER = "E:/upload/report/";
    
    
    
    //upload a report by lab
    @PostMapping("/uploadreport/{labId}/{role}") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
    							   @RequestParam("name") String name,
                                   RedirectAttributes redirectAttributes
                                   ,@PathVariable int labId,@PathVariable int role,ModelMap modelmap) {

    	
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        
        	if(patientservice.patientByNameAndAcive(name, 1) != null){
        		
        		try {
        			
        		// Get the report and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                
               Report report =new Report(patientservice.patientByName(name).getPatientId(),labId,file.getOriginalFilename(),UPLOADED_FOLDER);
               reportService.addReport(report);
                
                
                Files.write(path, bytes);

                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");
        		
                return "redirect:/reports/{labId}/{role}";
                
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        		
        	}
            
        	
        		modelmap.put("error", "Invalid user name");
        		
        		 return "redirect:/reports/{labId}/{role}";
        	
        		
        	
       
        	
    }
    
    
    
    
    //view report 
   @RequestMapping(value = "/downreport/{id}", method = RequestMethod.GET)
    public void getAdv(HttpServletResponse response,@PathVariable int id) {
        try {
          // get your file as InputStream
        	Report report = reportService.getOneReport(id);
        	
        	File file = new File(report.getLocation()+report.getFilename());
        	InputStream is = new FileInputStream(file);
        	
        	
          // copy it to response's OutputStream
        	org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
          response.flushBuffer();
        } catch (IOException ex) {
         // log.info("Error writing file to output stream. Filename was '{}'", "a", ex);
          throw new RuntimeException("IOError writing file to output stream");
        }

    }
    
    
   
   
   //delete report
    @RequestMapping(value="/deletereport/{reportId}/{id}/{role}")
  	public String deleteAdv(@PathVariable int reportId,@PathVariable int id,@PathVariable int role){
  		
    	reportService.deleteReport(reportId);
    	
    	
    		return "redirect:/reports/{id}/{role}";
      
      }
    
    

}
