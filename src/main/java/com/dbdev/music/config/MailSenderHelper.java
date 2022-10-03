package com.dbdev.music.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderHelper {

    @Value("${spring.mail.username}")
    String from;

    @Autowired
    private JavaMailSender mailSender;

    public String send(String to, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        String code = String.valueOf((int)(Math.random() * 999999));
        message.setText("您的验证码为" + code);
        System.out.println(message);
        mailSender.send(message);
        return code;
    }
}
