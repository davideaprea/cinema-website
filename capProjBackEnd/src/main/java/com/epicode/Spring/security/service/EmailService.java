package com.epicode.Spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired private JavaMailSender emailSender;
	
	public void sendPaymentConfirmation(String email){
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Epicinema | Thank you! Here's your payment receipt.");
        message.setText("<p>Your bookings have been confirmed. Your tickets can be found at <a>http://localhost:8080/profile/mytickets</a>.</p>");
        emailSender.send(message);
	}
	
	public void sendEmailConfirmation(String email, String token){
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Epicinema | Email confirmation");
        message.setText("Click on this link to verify your email: <a>http://localhost:4200/auth/account-verification?token="+token+"</a>");
        emailSender.send(message);
	}
}
