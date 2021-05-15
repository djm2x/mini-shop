package com.example.minishop.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class Mail {

	private static final Logger logger = LoggerFactory.getLogger(Mail.class);

	private List<InternetAddress> receiver;
	private String sender;
	private String subject;
	private String templateContent;

	@Autowired
	private JavaMailSender mailService;

	private static final Properties properties;

	static {
		properties = new Properties();
		try {
			File f = new ClassPathResource("application.properties").getFile();
			properties.load(new FileInputStream(f));
		} catch (Exception e) {
			logger.error("Problème lors du chargement du fichier application.properties : ", e);
		}
	}

	public void impl(String email) throws AddressException {
		// Calcul du paramètre encrypté dans le lien
		String inscriptionFile = "subscription_fr_fr.html";
		List<InternetAddress> receiverList = new ArrayList<InternetAddress>();
		receiverList.add(new InternetAddress(email));

		Mail m = new Mail(receiverList, properties.getProperty("spring.mail.username"), "CREATE_ACCOUNT", inscriptionFile);


		m.setVariable("email_compte", email);

		try {
			m.send();
		} catch (Exception e) {
		 e.getMessage();
		}
	}

	public Mail(List<InternetAddress> receiver, String sender, String subject, String templateName) {
		super();
		this.receiver = receiver;
		this.sender = sender;
		this.subject = subject;
		try {
			File f = new ClassPathResource("mails/" + templateName).getFile();
			List<String> l = Files.readAllLines(f.toPath());
			templateContent = String.join("\n", l);
		} catch (Exception e) {
			logger.error("Error while reading the template: ", e);
		}
	}

	public void setVariable(String name, String value) {
		templateContent = templateContent.replace("{{" + name + "}}", value);
	}

	public void send() {
		try {
			MimeMessage mimeMessage = mailService.createMimeMessage();
			mimeMessage.setContent(templateContent, "text/html; charset=ISO-8859-1");
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "ISO-8859-1");
			if (Boolean.valueOf(properties.getProperty("com.sportvalue.mail.to.dev"))) {
				receiver.add(new InternetAddress(properties.getProperty("com.sportvalue.dev.emails")));
				InternetAddress[] receiverArray = receiver.toArray(new InternetAddress[receiver.size()]);
				helper.setTo(receiverArray);
			} else {
				InternetAddress[] receiverArray = receiver.toArray(new InternetAddress[receiver.size()]);
				helper.setTo(receiverArray);
			}
			helper.setSubject(subject);
			helper.setFrom(sender);
			mailService.send(mimeMessage);
		} catch (Exception e) {
			logger.error("Problème lors de l'envoi de mail : ", e);

		}
	}

}
