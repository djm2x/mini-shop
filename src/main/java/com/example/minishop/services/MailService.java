package com.example.minishop.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

	private static final Logger logger = LoggerFactory.getLogger(MailService.class);

	@Autowired
	private Environment env;

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String email, String subject, String html) {
		try {
			String sender = env.getProperty("senderEmail");

			String to = email; // "mail1@gmail.com , mail2@gmail.com, mail3@gmail.com";
			InternetAddress[] receiverArray = InternetAddress.parse(to , true);

			MimeMessage mimeMessage = mailSender.createMimeMessage();

			mimeMessage.setContent(html, "text/html; charset=ISO-8859-1");

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "ISO-8859-1");


			helper.setTo(receiverArray);
			helper.setSubject(subject);
			helper.setFrom(sender);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			logger.error("Problème lors de l'envoi de mail : ", e);
			return false;
		}

		return true;
	}

}
