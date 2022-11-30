//package com.tdtu.finalproject.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import com.tdtu.finalproject.model.EmailSenderService;
//
//@Controller
//public class SendEmailController {
//
//	/*@Autowired
//	private JavaMailSender mailSender;*/
//
//	@Autowired
//	private EmailSenderService sender;
//
//	@GetMapping("/emailForm")
//	public String viewEmailForm() {
//		return "emailForm";
//	}
//
//	//Chay dc
////	@EventListener(ApplicationReadyEvent.class)
////	public void send() {
////		sender.sendEmail("namthongminhghe@gmail.com","123","123");
////	}
//
//
//	// Loi~ o? day, chi? chay dc ben tren
//	//send email
////	@PostMapping("/sendEmail")
////	public String doSendEmail(@RequestParam String recipient, @RequestParam String subject,@RequestParam String message,Model model) {
////		System.out.println("msg :"+message);
////		System.out.println("sub :"+subject);
////
////        /*model.addAttribute("recipient",recipient);
////        model.addAttribute("subject", subject);
////        model.addAttribute("message", message);*/
////
////		sender.sendEmail(recipient,message,subject);
////		return "emailForm";
////	}
//}