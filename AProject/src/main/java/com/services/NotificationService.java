package com.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.models.User;



@Service
public class NotificationService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public NotificationService(JavaMailSender javaMailSender){
		this.javaMailSender=javaMailSender;
		
	}
	
	public int sendNotification(String email)throws MailException{
		
		Random rand = new Random();
		int  code = rand.nextInt(9999) + 10000;
		
		//send email
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(email);
		mail.setFrom("karuna.koneshan1995@gmail.com");
		mail.setSubject("Browse For Care");
	    mail.setText("Your code is "+code);
		
	    javaMailSender.send(mail);
	    
	    return code;
	}
	
	
	
	
}
