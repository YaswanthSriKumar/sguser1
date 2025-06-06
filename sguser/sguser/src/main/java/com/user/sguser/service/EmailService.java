package com.user.sguser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendEmail(String to, String subject, String body) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        System.out.println("mail sender_____________________________________________");
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);
	        message.setFrom("yasswanthsri817@gmail.com");
	        mailSender.send(message);
	    }
}
