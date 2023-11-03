package com.example.agence_voyage_back.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// Slf4j : sert a configurer avec mon email
@Slf4j
@Component
public class EmailServiceImp implements EmailService{
@Autowired
private JavaMailSender emailSender;
    private JavaMailSender javaMailSender;

    @Autowired
    public void  EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }
    }

