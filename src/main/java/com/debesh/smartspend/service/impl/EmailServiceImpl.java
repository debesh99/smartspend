package com.debesh.smartspend.service.impl;

import com.debesh.smartspend.controller.CustomerController;
import com.debesh.smartspend.model.EmailModel;
import com.debesh.smartspend.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Async
    @Override
    public void sendEmail(EmailModel emailModel) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(sender);
            message.setTo(emailModel.getRecipient());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getMsgBody());

            LOGGER.info("Processing: Sending mail from sender: {} to receiver: {}", sender, emailModel.getRecipient());
            javaMailSender.send(message);
            LOGGER.info("Email sent successfully");
        } catch (Exception e) {
            System.out.println("Exception: "+e);
        }
    }
}
