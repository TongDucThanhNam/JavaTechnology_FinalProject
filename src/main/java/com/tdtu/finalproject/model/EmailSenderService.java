package com.tdtu.finalproject.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String toEmail,String subject,String message) {
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setFrom("thiendat111@gmail.com");
		msg.setTo(toEmail);
		msg.setText(message);
		msg.setSubject(subject);
		mailSender.send(msg);
	}
}
