package com.example.agence_voyage_back.Services;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
public interface EmailService {




     void sendEmail(SimpleMailMessage email) ;
}
