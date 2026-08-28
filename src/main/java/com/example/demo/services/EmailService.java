package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	public JavaMailSender javaMailSender;

	public void sendEmail(String fromEmail, String toEmail, String subject, String mailBody) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(mailBody);

		javaMailSender.send(message);
	}

	public void sendForgotEmail(String fromEmail, String toEmail, String emailSubject, String emailBody) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject(emailSubject);
		helper.setText(emailBody);
		javaMailSender.send(message);
	}

	public void sendHtmlEmail(String fromEmail, String toEmail, String mailSubject, String mailbody) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject(mailSubject);
		helper.setText(mailbody, true);
		javaMailSender.send(message);
	}

}
