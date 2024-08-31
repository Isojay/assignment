package com.assignment.Service.Implementation;

import com.assignment.Service.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for sending email notifications.
 * Uses Spring's JavaMailSender to send emails.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    /**
     * Sends a notification email to a recipient.
     *
     * @param message The content of the email to be sent.
     */
    public void sendNotification(String message,String email) {
        //Default recipient : "info.bijayshrestha@gmail.com" is set
        if (email == null) {
            email = "info.bijayshrestha@gmail.com";
        }
        sendEmailNotification(email, message);
        log.info("Notification sent: {}", message);
    }

    private void sendEmailNotification(String sendTo, String message) {
        if (sendTo == null || sendTo.isEmpty()) {
            return;
        }
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(sendTo);
            mailMessage.setText(message);
            mailMessage.setSubject("Item Purchase Notification");

            javaMailSender.send(mailMessage);
            log.info("Mail Sent Successfully...");
        } catch (Exception e) {
            log.error("Error while Sending Mail : {}", e.getMessage());
        }
    }
}
