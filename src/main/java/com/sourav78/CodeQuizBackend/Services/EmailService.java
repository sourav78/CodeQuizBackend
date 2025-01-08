package com.sourav78.CodeQuizBackend.Services;

import com.sourav78.CodeQuizBackend.Utils.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationMail(String to, String subject, String body) {
        // mail logic
        // Create a MimeMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            // Use MimeMessageHelper to set the properties
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom("noreply@codequiz.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(EmailTemplate.getVerificationEmailTemplate(body), true); // Set to true to indicate HTML content

            // Send the email
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }

    }
}
