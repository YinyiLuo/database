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

    public void sendCode(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("验证信息");
        message.setText("您的验证码为" + code);
        mailSender.send(message);
    }

    public void sendMessage(String email, String content, String title) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }
}
