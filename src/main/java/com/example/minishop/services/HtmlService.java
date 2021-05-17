package com.example.minishop.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
public class HtmlService {

    private static final Logger logger = LoggerFactory.getLogger(HtmlService.class);

    public String prepareRegisterHtml(String action_url, String email/*, String supportEmail*/) {
        String fileName = "registration_user.html";
        String strHTML = readFile(fileName);

        return strHTML.replace("{{action_url}}", action_url)
            .replace("{{email}}", email)
            // .replace("{{" + supportEmail + "}}", supportEmail)
            ;
    }

    private String readFile(String templateName) {
        try {
            File f = new ClassPathResource("mails/" + templateName).getFile();
            List<String> l = Files.readAllLines(f.toPath());
            return String.join("\n", l);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
