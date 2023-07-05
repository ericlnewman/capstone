package com.success.security.event;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.success.dto.UserDTO;
import com.success.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private UserService userService;
    private  JavaMailSender mailSender;

    private static final Logger log = LoggerFactory.getLogger(RegistrationCompleteEventListener.class);

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        UserDTO userDTO = event.getUserDTO();
        String verificationToken = generateVerificationToken();
        saveVerificationToken(userDTO, verificationToken);
        String verificationUrl = buildVerificationUrl(event.getApplicationUrl(), verificationToken);
        sendVerificationEmail(userDTO.getEmail(), verificationUrl);
        log.info("Click the link to verify your registration: {}", verificationUrl);
    }

    private String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }

    private void saveVerificationToken(UserDTO userDTO, String verificationToken) {
        userService.saveUserVerificationToken(userDTO, verificationToken);
    }

    private String buildVerificationUrl(String applicationUrl, String verificationToken) {
        return applicationUrl + "/register/verifyEmail?token=" + verificationToken;
    }

    private void sendVerificationEmail(String recipientEmail, String verificationUrl) {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = buildEmailContent(recipientEmail, verificationUrl);
        try {
            sendEmail(senderName, recipientEmail, subject, mailContent);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Failed to send verification email to {}: {}", recipientEmail, e.getMessage());
            // Handle the exception gracefully, such as logging the error, notifying the user, or taking appropriate actions
        }
    }

    private String buildEmailContent(String recipientEmail, String verificationUrl) {
        return "<p> Hi, " + recipientEmail + ", </p>" +
                "<p>Thank you for registering with us." +
                "Please, follow the link below to complete your registration.</p>" +
                "<a href=\"" + verificationUrl + "\">Verify your email to activate your account</a>" +
                "<p> Thank you <br> Users Registration Portal Service";
    }

    private void sendEmail(String senderName, String recipientEmail, String subject, String content)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("success@forall.com", senderName);
        messageHelper.setTo(recipientEmail);
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        mailSender.send(message);
    }
}
