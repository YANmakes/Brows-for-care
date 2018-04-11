package com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.models.*;
import com.services.*;

@Controller
public class ReplyController {
	
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="reply/{receiverid}")
	public String getReplys(@PathVariable int receiverid,ModelMap modelmap){
		modelmap.put("replys",replyService.replyByReceiver(receiverid));
		
		return "Patient/profile";
	}
	

	@RequestMapping(value="deletereply/{replyid}/{id}/{role}")
	public String deleteReply(@PathVariable int replyid,@PathVariable int id,@PathVariable int role){
		
		replyService.deleteOneReply(replyid);
		
		if(role==2)
			return "redirect:/patientnotification/{id}";
		
		else if(role==4)
			return "redirect:/doctornotification/{id}";
		else if(role==5)
			return "redirect:/clinicindex/{id}";
		else if(role==6)
			return "redirect:/labnotification/{id}";
		else
			return "";
	}
	
	@RequestMapping(value="/replied/{sender}/{role}")
	public String addreply(@RequestBody @ModelAttribute Reply reply,@PathVariable int sender,@PathVariable int role,ModelMap modelmap){
		
		Reply rep =new Reply(reply);
		replyService.addReply(rep);
		
	
		if(role==5)
			return "redirect:/clinicprofile/{sender}/{role}";
		else if(role==6)
			return "redirect:/labprofile/{sender}/{role}";
		else if(role==4)
			return "redirect:/doctorprofile/{sender}/{role}";
		else
			return "";
	}
	

	
}
