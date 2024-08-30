package com.assignment.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendNotification(String message) {
        sendNotification("info.bijayshrestha@gmail.com", message);
        log.info("Notification sent: {}", message);
    }

    public void sendNotification(String sendTo, String message) {
        if (sendTo == null || sendTo.isEmpty()) {
            return;
        }
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(sendTo);
            mailMessage.setText(message);
            mailMessage.setSubject("Item Notification");

            javaMailSender.send(mailMessage);
            log.info("Mail Sent Successfully...");
        } catch (Exception e) {
            log.error("Error while Sending Mail : {}", e.getMessage());
        }
    }
}
